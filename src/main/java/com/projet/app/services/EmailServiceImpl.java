// Implémentation du service d'envoi d'email utilisant JavaMailSender.
package com.projet.app.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService{
	private String fromEmail;
	private JavaMailSender javaMailSender;
	public EmailServiceImpl(@Value("${spring.mail.username}")String fromEmail,JavaMailSender javaMailSender) {
			this.fromEmail=fromEmail;
			
			this.javaMailSender = javaMailSender;
		}
	@Override
	public String sendEmail( String to, String cc, String subject, String body) {
		try {
			
			    MimeMessage mimeMessage=this.javaMailSender.createMimeMessage();
			    MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
			    mimeMessageHelper.setFrom(fromEmail);
	            mimeMessageHelper.setTo(to);
	            mimeMessageHelper.setCc(cc);
	            mimeMessageHelper.setSubject(subject);
	            mimeMessageHelper.setText(body);
                javaMailSender.send(mimeMessage);

	            return "mail send";
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
		
	}


}
