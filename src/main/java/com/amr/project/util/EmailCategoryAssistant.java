package com.amr.project.util;

import com.amr.project.model.Mail;
import com.amr.project.model.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailCategoryAssistant {
    private final EmailServiceAssistant emailServiceAssistant;

    public Mail trackedEmailCategoryCreated(Category category) {
        Mail mail = new Mail();
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        mail.setTo(userEmail);
        mail.setText("Created new category:  " + category.getName());
        return mail;
    }

    public Mail trackedEmailCategoryUpdate(Category category) {
        Mail mail = new Mail();
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        mail.setTo(userEmail);
        mail.setText("Category changed: " + category.getName());
        return mail;
    }

    public Mail trackedEmailCategoryDelete(Long id) {
        Mail mail = new Mail();
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        mail.setTo(userEmail);
        mail.setText("Category delete" + emailServiceAssistant.getCategoryRepository().getById(id).getName());
        return mail;
    }
}
