package com.blog.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String message) {

        SimpleMailMessage S = new SimpleMailMessage();
        S.setTo(to);
        S.setSubject(subject);
        S.setText(message);

        javaMailSender.send(S);
    }


}
