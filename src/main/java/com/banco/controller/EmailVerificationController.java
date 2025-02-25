package com.banco.controller;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Slf4j
@RestController
@RequestMapping("/verification")
public class EmailVerificationController {

    private static final Logger log = LoggerFactory.getLogger(EmailVerificationController.class);

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/send-code")
    public ResponseEntity<?> sendCode(@RequestBody Map<String, String> request) {
        log.debug("Iniciando proceso de envío de código de verificación");
        try {
            String email = request.get("email");
            log.debug("Email recibido: {}", email);

            if (email == null || email.trim().isEmpty()) {
                log.error("Email no proporcionado");
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "Email is required"));
            }

            String code = generateRandomCode();
            log.debug("Código generado: {}", code);

            try {
                sendEmail(email, "Your verification code", "Your code is: " + code);
                log.info("Código de verificación enviado exitosamente a: {}", email);
            } catch (MailException e) {
                log.error("Error al enviar el email: {}", e.getMessage(), e);
                throw e;
            }

            Map<String, String> response = new HashMap<>();
            response.put("message", "Verification code was sent successfully");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("Error en el proceso de verificación: {}", e.getMessage(), e);
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Failed to send email: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorResponse);
        }
    }

    private String generateRandomCode() {

        Random random = new Random();

        int code = 100000 + random.nextInt(9000000);

        return String.valueOf(code);
    }

    private void sendEmail(String to, String subject, String text) {
        log.debug("Preparando para enviar email a: {}", to);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("julianhomezdev@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        try {
            mailSender.send(message);
            log.debug("Email enviado exitosamente");
        } catch (MailException e) {
            log.error("Error en el envío del email: {}", e.getMessage());
            throw e;
        }
    }
}