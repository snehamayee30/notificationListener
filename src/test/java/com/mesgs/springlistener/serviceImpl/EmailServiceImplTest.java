package com.mesgs.springlistener.serviceImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.mesgs.springlistener.entity.EmailDetails;

public class EmailServiceImplTest {
	
    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private EmailServiceImpl emailService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testSendSimpleMail_Error() {
        // Prepare test data
        EmailDetails details = new EmailDetails("recipient@example.com", "Test message", "Subject");

        // Mock JavaMailSender to throw an exception
        doThrow(new RuntimeException("Sending failed")).when(javaMailSender).send(any(SimpleMailMessage.class));

        // Invoke the sendSimpleMail method
        String result = emailService.sendSimpleMail(details);

        // Verify that JavaMailSender.send() is called with the correct arguments
        verify(javaMailSender).send(any(SimpleMailMessage.class));

        // Verify the returned result
        assertEquals("Error while Sending Mail", result);
    }
}

