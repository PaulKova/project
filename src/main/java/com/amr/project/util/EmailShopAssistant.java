package com.amr.project.util;

import com.amr.project.model.Mail;
import com.amr.project.model.entity.Shop;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmailShopAssistant {
    private final EmailServiceAssistant emailServiceAssistant;

    public Mail trackEmailShopCreate(Shop shop) {
        Mail mail = new Mail();
        mail.setTo(shop.getUser().getEmail());
        mail.setText("Shop created: " + shop.getName());
        return mail;
    }

    public Mail trackEmailShopUpdate(Shop shop) {
        Mail mail = new Mail();
        Shop shopOriginal = emailServiceAssistant.getShopRepository().getById(shop.getId());
        String message = "";

        if(!shopOriginal.getName().equals(shop.getName())) {
            message += "name: " + shop.getName();
        }
        if(!shopOriginal.getEmail().equals(shop.getEmail())) {
            message += "Email: " + shop.getEmail();
        }
        if(!shopOriginal.getPhone().equals(shop.getPhone())) {
            message += "phone: " + shop.getPhone();
        }
        if(!shopOriginal.getDescription().equals(shop.getDescription())) {
            message += "Description: " + shop.getDescription();
        }

        mail.setText(message);

        return mail;
    }

    public Mail trackEmailShopDelete(Shop shop) {
        Mail mail = new Mail();
        mail.setTo(shop.getUser().getEmail());
        mail.setText("Shop delete: " + shop.getName());
        return mail;
    }

}
