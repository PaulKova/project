package com.amr.project.util;

import com.amr.project.model.Mail;
import com.amr.project.model.entity.Coupon;
import com.amr.project.model.entity.Discount;
import com.amr.project.model.entity.User;
import com.amr.project.model.entity.UserInfo;
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
        UserInfo userInfoOriginal = userOriginal.getUserInfo();

        if (!userInfoOriginal.getUser().getUsername().equals(user.getUsername())) {
            message.append("username: ").append(user.getUsername());
        }
        if (!userInfoOriginal.getUser().getEmail().equals(user.getEmail())) {
            message.append("email : ").append(user.getEmail());
        }
        if (!userInfoOriginal.getUser().getPassword().equals(user.getPassword())) {
            message.append("password been changed");
        }
        //
        if (!userInfoOriginal.getPhone().equals(user.getUserInfo().getPhone())) {
            message.append("Phone: ").append(user.getUserInfo().getPhone());
        }
        if (!userInfoOriginal.getFirstName().equals(user.getUserInfo().getFirstName())) {
            message.append("FirstName: ").append(user.getUserInfo().getFirstName());
        }
        if (!userInfoOriginal.getLastName().equals(user.getUserInfo().getLastName())) {
            message.append("LastName: ").append(user.getUserInfo().getLastName());
        }
        if (userInfoOriginal.getAge() != (user.getUserInfo().getAge())) {
            message.append("Age: ").append(user.getUserInfo().getAge());
        }
        if (!userInfoOriginal.getGender().equals(user.getUserInfo().getGender())) {
            message.append("Gender: ").append(user.getUserInfo().getGender());
        }
        if (!userInfoOriginal.getBirthday().equals(user.getUserInfo().getBirthday())) {
            message.append("Birthday: ").append(user.getUserInfo().getBirthday());
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
