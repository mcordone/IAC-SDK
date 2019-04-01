package com.iac.sdk.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class PropertyTest {
    private static String API_URL = "http://localhost:8080/event";
    private static int RETRY_ATTEMPT_NUMBER = 3;
    private static long RETRY_TIME_INTERVAL = 3000;

    @Test
    public void getApiUrl() {
        String apiUrl = Property.getApiUrl();
        assertEquals(API_URL, apiUrl);
    }

    @Test
    public void getRetryNumber() {
        int retryNumber = Property.getRetryNumber();
        assertEquals(RETRY_ATTEMPT_NUMBER, retryNumber);
    }

    @Test
    public void getRetryInterval() {
        long retryInterval = Property.getRetryInterval();
        assertEquals(RETRY_TIME_INTERVAL, retryInterval);
    }
}