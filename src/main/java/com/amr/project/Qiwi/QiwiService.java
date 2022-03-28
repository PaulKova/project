package com.amr.project.Qiwi;

import com.qiwi.billpayments.sdk.client.BillPaymentClient;
import com.qiwi.billpayments.sdk.client.BillPaymentClientFactory;
import com.qiwi.billpayments.sdk.model.MoneyAmount;
import com.qiwi.billpayments.sdk.model.in.CreateBillInfo;
import com.qiwi.billpayments.sdk.model.in.Customer;
import com.qiwi.billpayments.sdk.model.out.BillResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Currency;
import java.util.UUID;

@Service
public class QiwiService {

    private final String secretKey = "eyJ2ZXJzaW9uIjoiUDJQIiwiZGF0YSI6eyJwYXlpbl9tZXJjaGFudF9zaXRlX3VpZCI6ImRtcDN5Ny0wMCIsInVzZXJfaWQiOiI3OTE2NTc0NTI3OCIsInNlY3JldCI6IjI2M2Y0M2FiYzU5NzFjNWRjOWQ0YTk0YTA4MGIyY2YwMTRlMzcwNWM0YjEzYjRhNTE1NzEwNmRlZjg3MGY4NGQifX0=";
    BillPaymentClient client = BillPaymentClientFactory.createDefault(secretKey);

    public BillResponse createBill(int sum, String comment, String email, String phone, String successUrl) {
        CreateBillInfo billInfo = new CreateBillInfo(
                UUID.randomUUID().toString(),
                new MoneyAmount(
                        BigDecimal.valueOf(sum),
                        Currency.getInstance("RUB")
                ),
                comment,
                ZonedDateTime.now().plusSeconds(45),
                new Customer(
                        email,
                        UUID.randomUUID().toString(),
                        phone
                ),
                successUrl
        );
        try {
            return client.createBill(billInfo);
        } catch (Exception e){
            return null;
        }
    }

    public String paymentStatus(String id){
        return client.getBillInfo(id).getStatus().getValue().getValue();
    }
}

