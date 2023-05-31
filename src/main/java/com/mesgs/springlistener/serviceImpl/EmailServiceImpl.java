package com.mesgs.springlistener.serviceImpl;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.mesgs.springlistener.entity.EmailDetails;
import com.mesgs.springlistener.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String sender;

	@Override
	public String sendSimpleMail(EmailDetails details) {

		// Try block to check for exceptions
		try {
			
			
			MimeMessage message = javaMailSender.createMimeMessage();
			// Creating a simple mail message
			//SimpleMailMessage mailMessage = new SimpleMailMessage();
			 MimeMessageHelper mailMessage = new MimeMessageHelper(message, true);


			// Setting up necessary details
			mailMessage.setFrom(sender);
			mailMessage.setTo(details.getRecipient());
			mailMessage.setText(details.getMsgBody(),true);
			mailMessage.setSubject(details.getSubject());


			// Sending the mail
			javaMailSender.send(message);
			return "Mail Sent Successfully...";
		}

		// Catch block to handle the exceptions
		catch (Exception e) {
			return "Error while Sending Mail";
		}

	}

}
