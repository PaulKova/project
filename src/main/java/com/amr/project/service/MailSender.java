package com.amr.project.service;

import com.amr.project.model.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSender {

    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String username;

    public void send(Mail mail){
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(username);
        mailMessage.setTo(mail.getTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getText());

        mailSender.send(mailMessage);
    }
}
