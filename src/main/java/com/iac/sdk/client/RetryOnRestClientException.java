package com.iac.sdk.client;

import com.iac.sdk.config.Property;
import com.iac.sdk.exception.RetryLimitException;

public class RetryOnRestClientException {
    private static int RETRY_NUMBER_DEFAULT = 3;
    private static long RETRY_TIME_INTERVAL_DEFAULT = 3000;

    private int retryNumber;
    private long retryTimeInterval;

    public RetryOnRestClientException() {
        retryNumber = (Property.getRetryNumber() <= 0) ? RETRY_NUMBER_DEFAULT : Property.getRetryNumber();
        retryTimeInterval = (Property.getRetryInteral() <= 0) ? RETRY_TIME_INTERVAL_DEFAULT : Property.getRetryInteral();
    }

    /**
     * Check if number of retry is still within limit
     * @return boolean
     */
    public boolean isRetryable() {

        return (retryNumber >= 0);
    }

    /**
     * Put current thread to sleep until next retry
     * @throws InterruptedException
     */
    public void waitTillNextRetry() {
        try {
            Thread.sleep(retryTimeInterval);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check if the number of retry has reached the limit
     * @throws RetryLimitException
     */
    public void onException() throws RetryLimitException
    {
        retryNumber--;
        if(!isRetryable())
        {
            retryNumber = (Property.getRetryNumber() <= 0) ? RETRY_NUMBER_DEFAULT : Property.getRetryNumber();
            throw new RetryLimitException("Retry limit exceeded.");
        }

        waitTillNextRetry();
    }
}
