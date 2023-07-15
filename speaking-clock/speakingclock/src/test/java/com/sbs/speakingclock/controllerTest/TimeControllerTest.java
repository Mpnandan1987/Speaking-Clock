package com.sbs.speakingclock.controllerTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sbs.speakingclock.controller.TimeController;
import com.sbs.speakingclock.service.TimeService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
    
}