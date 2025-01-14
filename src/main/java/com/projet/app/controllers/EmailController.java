//Permet d'envoyer des emails via un service de messagerie
package com.projet.app.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projet.app.dto.EmailDTO;
import com.projet.app.services.EmailService;

@RestController

public class EmailController {
	
	private EmailService emailService;
	
	 public EmailController(EmailService emailService) {
	        this.emailService = emailService;
	    }
	 
	 @PostMapping("/sendmail")
	    public String sendMail(@RequestBody EmailDTO emailDTO) {
		 return this.emailService.sendEmail(emailDTO.getTo(), emailDTO.getCc(), emailDTO.getSubject(), emailDTO.getBody());
	    }

}
