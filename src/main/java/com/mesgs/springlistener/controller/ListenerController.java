package com.mesgs.springlistener.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mesgs.springlistener.entity.EmailDetails;
import com.mesgs.springlistener.entity.Messages;
import com.mesgs.springlistener.repository.MessagingRepo;
import com.mesgs.springlistener.service.EmailService;


@RestController
public class ListenerController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ListenerController.class);
	private static final String QUEUE_NAME = "spring-order-queue";

	@Autowired
	private MessagingRepo messageRepo;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@JmsListener(destination = QUEUE_NAME, containerFactory = "jmsListenerContainerFactory")
	public void receiveMessage(byte[] mesgs) throws StreamReadException, DatabindException, IOException, MessagingException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		Messages mesgObj = objectMapper.readValue(mesgs, Messages.class);
		LOGGER.info("Message received:: {}", mesgObj.toString());
		messageRepo.save(mesgObj);
		LOGGER.info("Message stored to DB:: {}", mesgObj.toString());
		
		//read html file
		//read content and pass that in below
		String filePath = "src/main/resources/templates/emailcontent.html";
		String htmlContent="";
		
		try {
			String htmlContentraw = readFile(filePath);
            htmlContent = htmlContentraw.replace("{productID}", mesgObj.getProductID()).
            		replace("{productName}", mesgObj.getProductName()).replace("{quantity}",String.valueOf(mesgObj.getQuantity()));
            //System.out.println(htmlContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setText(htmlContent,true);
		emailService.sendSimpleMail(new EmailDetails("snehamayees@kpmg.com",htmlContent,"Order Confirmation Notification")); 
		LOGGER.info("Email Sent Successfully!!!");
	}
	
	
	private static String readFile(String filePath) throws IOException {
        byte[] encodedBytes = Files.readAllBytes(Paths.get(filePath));
        return new String(encodedBytes);
    }


	@GetMapping("/allDataFromDb")
	public List<Messages> fetchAllDatafromDB(){
		return messageRepo.findAll();
	}
}
