package com.amr.project.util;

import com.amr.project.model.Mail;
import com.amr.project.model.entity.ContactForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailContactFormAssistant {

    public Mail trackedEmailContactFormUpdate(ContactForm contactForm) {
        Mail mail = new Mail();
        String userEmail = contactForm.getEmail();
        mail.setTo(userEmail);
        mail.setText(contactForm.getText());
        return mail;
    }
}
