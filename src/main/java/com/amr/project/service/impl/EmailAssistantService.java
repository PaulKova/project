package com.amr.project.service.impl;

import com.amr.project.model.Mail;
import com.amr.project.model.entity.Order;
import com.amr.project.util.EmailOrderAssistant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailAssistantService {

    private final EmailOrderAssistant emailOrderAssistant;

    public Mail trackerEmailOrderSave(Order order) {
        Mail mail = new Mail();
        mail.setTo(order.getUser().getEmail());
        mail.setText("Order has been created № " + order.getId() + ". Status: " + order.getStatus());
        return mail;
    }

    public Mail trackerEmailOrderUpdate(Order order) {
        Order oldOrder = emailOrderAssistant.getOrderRepository().getById(order.getId());
        Mail mail = new Mail();
        String text = "";
        if(!oldOrder.getStatus().equals(order.getStatus())) {
            text += "New status of order " + order.getStatus();
        }
        mail.setTo(order.getUser().getEmail());
        mail.setText(text);
        return mail;
    }

    public Mail trackerEmailOrderDelete(Order order) {
        Mail mail = new Mail();
        mail.setTo(order.getUser().getEmail());
        mail.setText("Order cancel " + order.getId());
        return mail;
    }
}
