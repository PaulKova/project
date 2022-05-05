package com.amr.project.util;

import com.amr.project.model.Mail;
import com.amr.project.model.entity.Coupon;
import com.amr.project.model.entity.Discount;
import com.amr.project.model.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@AllArgsConstructor
public class EmailUserAssistant {

    private final EmailServiceAssistant emailServiceAssistant;

    public Mail trackedEmailUserUpdate(User user) {
        Mail mail = new Mail();
        StringBuilder message = new StringBuilder("User changes: ");

        User userOriginal = emailServiceAssistant.getUserRepository().getUserByEmail(user.getEmail());

        if (!userOriginal.getUsername().equals(user.getUsername())) {
            message.append("username: ").append(user.getUsername());
        }
        if (!userOriginal.getEmail().equals(user.getEmail())) {
            message.append("email : ").append(user.getEmail());
        }
        if (!userOriginal.getPassword().equals(user.getPassword())) {
            message.append("password been changed");
        }
        //
        if (!userOriginal.getPhone().equals(user.getPhone())) {
            message.append("Phone: ").append(user.getPhone());
        }
        if (!userOriginal.getFirstName().equals(user.getFirstName())) {
            message.append("FirstName: ").append(user.getFirstName());
        }
        if (!userOriginal.getLastName().equals(user.getLastName())) {
            message.append("LastName: ").append(user.getLastName());
        }
        if (userOriginal.getAge() != (user.getAge())) {
            message.append("Age: ").append(user.getAge());
        }
        if (!userOriginal.getGender().equals(user.getGender())) {
            message.append("Gender: ").append(user.getGender());
        }
        if (!userOriginal.getBirthday().equals(user.getBirthday())) {
            message.append("Birthday: ").append(user.getBirthday());
        }

        Set<Discount> discountsOriginal = userOriginal.getDiscounts();
        Set<Discount> discounts = user.getDiscounts();

        for(Discount discount: discounts) {
            if (!discountsOriginal.contains(discount)) {
                message.append("Added coupon ").append(discount.getId());
            }
        }

        for(Discount discount: discounts) {
            if (!discounts.contains(discount)) {
                message.append("Delete coupon ").append(discount.getId());
            }
        }

        List<Coupon> couponsOriginal = userOriginal.getCoupons();
        List<Coupon> coupons = user.getCoupons();

        for(Coupon coupon: coupons) {
            if (!couponsOriginal.contains(coupon)) {
                message.append("Added coupon ").append(coupon.getId()).append(coupon.getEnd());
            }
        }

        for(Coupon coupon: coupons) {
            if (!coupons.contains(coupon)) {
                message.append("Delete coupon ").append(coupon.getId()).append(coupon.getEnd());
            }
        }

        mail.setTo(user.getEmail());
        mail.setText(message.toString());

        return mail;
    }

    public Mail trackedEmailUserDelete(User user) {
        Mail mail = new Mail();
        String message = "Account delete: " + user.getEmail();
        mail.setTo(user.getEmail());
        mail.setText(message);
        return mail;
    }

    public Mail trackedEmailIdentificationUpdate(User user) {
        Mail mail = new Mail();
        mail.setTo(user.getEmail());
        mail.setText("new Status: " + user.getPersonalData().getStatus().name() + "Comment: "
                + user.getPersonalData().getComment());
        return mail;
    }
}
