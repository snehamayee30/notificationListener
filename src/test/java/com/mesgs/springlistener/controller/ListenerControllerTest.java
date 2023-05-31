package com.mesgs.springlistener.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import com.mesgs.springlistener.entity.Messages;
import com.mesgs.springlistener.repository.MessagingRepo;
import com.mesgs.springlistener.service.EmailService;

public class ListenerControllerTest {
	
	@Mock
    private Logger logger;

	@Mock
	private MessagingRepo messagingRepo;

	@Mock
	private EmailService emailService;

	@InjectMocks
	private ListenerController listenerController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFetchAllDatafromDB() {
		List<Messages> messages = Arrays.asList(new Messages("A01", "PR-A", 3), new Messages("B01", "PR-B", 1));

		// Mock the behavior of messageRepo.findAll()
		when(messagingRepo.findAll()).thenReturn(messages);

		List<Messages> result = listenerController.fetchAllDatafromDB();

		// Verify that messageRepo.findAll() is called
		verify(messagingRepo).findAll();

		// Verify the returned result
		assertEquals(messages, result);
	}
}
