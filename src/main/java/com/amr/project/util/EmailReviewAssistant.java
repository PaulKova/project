package com.amr.project.util;

import com.amr.project.model.Mail;
import com.amr.project.model.entity.Review;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmailReviewAssistant {

    public Mail trackedEmailReviewSave(Review review) {
        Mail mail = new Mail();
        mail.setTo(review.getShop().getEmail());
        mail.setText("Shop : " + review.getShop().getName() + "new review for item "
                + review.getItem().getName());
        return mail;
    }

    public Mail trackedEmailReviewDelete(Review review) {
        Mail mail = new Mail();
        mail.setTo(review.getShop().getEmail());
        mail.setText("Shop: " + review.getShop().getName() + "item " +
                review.getItem().getName() + "delete - " + review.getText());
        return mail;
    }
}
