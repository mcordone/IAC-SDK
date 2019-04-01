package com.iac.sdk.client;

import com.iac.sdk.exception.RetryLimitException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class RetryOnRestClientExceptionTest {
    private static int RETRY_NUMBER_DEFAULT = 3;
    private static long RETRY_TIME_INTERVAL_DEFAULT = 1000;

    private RetryOnRestClientException objectUnderTest;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        objectUnderTest = new RetryOnRestClientException(RETRY_NUMBER_DEFAULT, RETRY_TIME_INTERVAL_DEFAULT);
    }

    @Test
    public void isRetryableShouldReturnTrueWithDefaultNumberOfRetries() {
        objectUnderTest.onException();
        assertTrue(objectUnderTest.isRetryable());
        objectUnderTest.onException();
        assertTrue(objectUnderTest.isRetryable());
        objectUnderTest.onException();
        assertTrue(objectUnderTest.isRetryable());
    }

    @Test
    public void isRetryableThrowsRetryLimitExceptionWhenRetriesLimitExceeded() {

        expectedException.expect(RetryLimitException.class);
        expectedException.expectMessage("Retry limit exceeded.");

        objectUnderTest.onException();
        assertTrue(objectUnderTest.isRetryable());
        objectUnderTest.onException();
        assertTrue(objectUnderTest.isRetryable());
        objectUnderTest.onException();
        assertTrue(objectUnderTest.isRetryable());
        objectUnderTest.onException();
        assertTrue(objectUnderTest.isRetryable());
    }
}