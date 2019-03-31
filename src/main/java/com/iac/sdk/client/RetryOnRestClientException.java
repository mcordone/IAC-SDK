package com.iac.sdk.client;

import com.iac.sdk.exception.RetryLimitException;

public class RetryOnRestClientException {
    private int retryAmount;
    private long retryTimeWait;

    public RetryOnRestClientException(Integer _retryAmount, Long _retryTimeWait) {
        retryAmount = _retryAmount;
        retryTimeWait = _retryTimeWait;
    }

    /**
     *
     * @return
     */
    public boolean isRetryable() {

        return (retryAmount >= 0);
    }

    /**
     *
     */
    public void waitTillNextRetry() {
        try {
            Thread.sleep(retryTimeWait);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @throws Exception
     */
    public void onError() throws RuntimeException
    {
        retryAmount--;
        if(!isRetryable())
        {
            retryAmount = 3;
            throw new RetryLimitException("Retry limit exceeded.");
        }

        waitTillNextRetry();
    }
}
