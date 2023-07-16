package com.sbs.speakingclock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Maheshwar Prasad
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpeakingClockApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testConvertToWords_Success() {
		String url = "http://localhost:" + port + "/convert?time=08:34";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("It's eight thirty-four", response.getBody());
	}

	@Test
	public void testConvertToWords_InvalidFormat() {
		String url = "http://localhost:" + port + "/convert?time=08:34:59";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("Invalid time format", response.getBody());
	}

	@Test
	public void whenEnteredMidnight_thenShouldReturnItsMidnight() {
		String url = "http://localhost:" + port + "/convert?time=24:00";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("It's Midnight", response.getBody());
	}

	@Test
	public void whenEnteredMidday_thenShouldReturnItsMidday() {
		String url = "http://localhost:" + port + "/convert?time=12:00";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("It's Midday", response.getBody());
	}

	@Test
	public void whenEnteredTwelveFifty_thenShouldReturnItsTwelveFifty() {
		String url = "http://localhost:" + port + "/convert?time=12:50";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("It's twelve fifty", response.getBody());

	}

	@Test
	public void whenEnteredOne_thenShouldReturnItsOne() {
		String url = "http://localhost:" + port + "/convert?time=13:00";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("It's one ", response.getBody());
	}

	@Test
	public void whenEnteredTwoOhSix_thenShouldReturnItsTwoOhSix() {
		String url = "http://localhost:" + port + "/convert?time=14:06";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("It's two six", response.getBody());
	}

	@Test
	public void whenEnteredFiveFifteen_thenShouldReturnItsFiveFifteen() {
		String url = "http://localhost:" + port + "/convert?time=17:15";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("It's five fifteen", response.getBody());
	}

}
