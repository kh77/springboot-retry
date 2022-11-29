package com.sm.exception;

public class RetryException extends Exception {

    private String message;

    public RetryException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
