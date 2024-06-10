package com.tattooart.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String body) {
	// Crear una nueva instancia de SimpleMailMessage
        SimpleMailMessage message = new SimpleMailMessage();
	// Establecer el destinatario del correo electrónico
        message.setTo(to);
	// Establecer el asunto del correo electrónico
        message.setSubject(subject);
	// Establecer el cuerpo del correo electrónico
        message.setText(body);
	// Enviar el correo electrónico utilizando JavaMailSender
        javaMailSender.send(message);
    }
}
