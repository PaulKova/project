package com.amr.project.Qiwi;

import com.qiwi.billpayments.sdk.model.out.BillResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class QiwiController {

    private final QiwiService qiwiService;

    public QiwiController(QiwiService qiwiService) {
        this.qiwiService = qiwiService;
    }

    @GetMapping("/order/pay")
    public String payOrder(){
        BillResponse billResponse = qiwiService.createBill(2, "test pay",
                "test@test.com", "79251234123",
                "http://localhost:8080/api/order/checkPay"); //почему-то не переходит по ссылке после оплаты((
        System.out.println(billResponse.getBillId());
        String redirectUrl = billResponse.getPayUrl();
        return "redirect:" + redirectUrl;
    }

    @GetMapping("order/checkPay/{id}")
    public String payChek(@PathVariable(name = "id") String id){
        System.out.println(qiwiService.paymentStatus(id));
        return "redirect:/";
    }

}
