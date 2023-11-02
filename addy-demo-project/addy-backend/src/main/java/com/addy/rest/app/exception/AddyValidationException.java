package com.addy.rest.app.exception;

public class AddyValidationException extends Exception {

    private static final long serialVersionUID = 1L;

    public AddyValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddyValidationException(String message) {
        super(message);
    }

    public AddyValidationException(String msg, Exception e) {
        super(msg + " because of " + e.toString());
    }

}