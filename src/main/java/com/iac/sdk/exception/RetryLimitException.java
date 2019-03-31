package com.iac.sdk.exception;

public class RetryLimitException extends RuntimeException {
    public RetryLimitException(String message) {
        super(message);
    }

    public RetryLimitException(String message, Throwable cause) {
        super(message, cause);
    }
}
