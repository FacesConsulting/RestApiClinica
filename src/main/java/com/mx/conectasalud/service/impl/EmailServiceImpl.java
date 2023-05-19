package com.mx.conectasalud.service.impl;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.conectasalud.service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
@Transactional
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(String to, String subject, String body) throws MessagingException, UnsupportedEncodingException {
        //String toAddress = user.getEmail();
        String fromAddress = "consultayaadmin@consulta-ya.com.mx";
        String senderName = "Consulta Ya";
        subject = "Please verify your registration";
        String content = "Dear Usuario,<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3>VERIFICAR</h3>"
                + "Thank you,<br>"
                + "Your company name.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(to);
        helper.setSubject(subject);

        // content = content.replace("[[name]]", user.getFullName());
        // String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();

        //content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);
    }
}
