package com.sbs.speakingclock.controllerTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.view.RedirectView;

import com.sbs.speakingclock.controller.TimeController;
import com.sbs.speakingclock.service.TimeService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalTime;

/**
 * @author Maheshwar Prasad
 */

public class TimeControllerTest {

	@InjectMocks
	private TimeController timeController;

	@Mock
	private TimeService timeService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testRedirectToSwaggerUI() {
		RedirectView redirectView = timeController.redirectToSwaggerUI();

		assertEquals("/swagger-ui.html", redirectView.getUrl());
	}

	@Test
	public void testGetCurrentTimeInWords() {
		ResponseEntity<String> response = timeController.getCurrentTimeInWords();

		assertEquals(HttpStatus.OK, response.getStatusCode());

		// Get the current time and convert it to words
		LocalTime currentTime = LocalTime.now();
		int hours = currentTime.getHour();
		int minutes = currentTime.getMinute();
		String expectedResponse = "It's " + timeController.convertTimeToWord(hours, minutes);

		assertEquals(expectedResponse, response.getBody());
	}

	@Test
	public void testConvertToWords_Success() {
		String timeStr = "08:34";
		String expectedResponse = "It's eight thirty-four";

		when(timeService.convertToWords(timeStr)).thenReturn(expectedResponse);

		ResponseEntity<String> response = timeController.convertToWords(timeStr);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedResponse, response.getBody());
	}

	@Test
	public void testConvertToWords_InvalidFormat() {
		String timeStr = "08:34:59";

		ResponseEntity<String> response = timeController.convertToWords(timeStr);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("Invalid time format", response.getBody());
	}

	@Test
	public void testHandleIllegalArgumentException() {
		IllegalArgumentException ex = new IllegalArgumentException("Invalid time format");

		ResponseEntity<String> response = timeController.handleIllegalArgumentException(ex);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("Invalid time format", response.getBody());
	}

	@Test
	public void testHandleException() {
		Exception ex = new Exception("An error occurred");

		ResponseEntity<String> response = timeController.handleException(ex);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertEquals("An error occurred", response.getBody());
	}

	@Test
	public void testConvertToWords_ExceptionThrown() {
		// Test case for exception thrown in the catch block
		String timeStr = "25:00";

		ResponseEntity<String> response = timeController.convertToWords(timeStr);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("Invalid time format", response.getBody());
	}
}