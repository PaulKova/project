package com.amr.project.util;

import com.amr.project.model.Mail;
import com.amr.project.model.entity.Category;
import com.amr.project.model.entity.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmailItemAssistant {
    private final EmailServiceAssistant emailServiceAssistant;

    public Mail trackedEmailItemSave(Item item) {
        Mail mail = new Mail();

        String shopEmail = item.getShop().getEmail();
        mail.setTo(shopEmail);
        mail.setText("Item add with id : " + item.getId() + ", name : " + item.getName() + ", description " + item.getDescription());
        return mail;
    }

    public Mail trackedEmailItemUpdate(Item item) {

        Mail mail = new Mail();

        Item originalItem = emailServiceAssistant.getItemRepository().getById(item.getId());
        String message = "ТоваР был изменен: ";
        if (!item.getName().equals(originalItem.getName())) {
            message += "name: " + item.getName();
        }
        if (item.getCount() != (originalItem.getCount())) {
            message += "count : " + item.getCount();
        }
        if (!item.getPrice().equals(originalItem.getPrice())) {
            message += "price: " + item.getPrice();
        }
        if (!item.getDescription().equals(originalItem.getDescription())) {
            message += "Description: " + item.getName();
        }
        mail.setText(message);
        return mail;

    }

    public Mail trackedEmailItemDelete(Item item) {
        Mail mail = new Mail();
        if (item.isPretendedToBeDeleted()) {
            String message = "Item delete " + item.getName() + "from shop " + item.getShop();
            mail.setText(message);
            mail.setTo(item.getShop().getEmail());
        }
        return mail;
    }
}
