package com.mx.conectasalud.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mx.conectasalud.service.EmailService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/verification")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping(path = "email-user")
    public ResponseEntity<String> sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String body) {
        try {
            emailService.sendEmail(to, subject, body);
            return ResponseEntity.ok("Email sent successfully");
        } catch (Exception e) {
            log.info("Errores: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending email: " + e.getMessage());
        }
    }

}
