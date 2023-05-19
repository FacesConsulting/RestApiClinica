package com.mx.conectasalud.service;

import java.io.UnsupportedEncodingException;

import jakarta.mail.MessagingException;

public interface EmailService {
    public void sendEmail(String to, String subject, String body) throws MessagingException, UnsupportedEncodingException;
}
