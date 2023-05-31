package com.mesgs.springlistener.service;

import com.mesgs.springlistener.entity.EmailDetails;

public interface EmailService {

	String sendSimpleMail(EmailDetails details);
}
