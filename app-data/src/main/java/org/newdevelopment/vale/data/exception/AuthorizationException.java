package org.newdevelopment.vale.data.exception;

import org.springframework.http.HttpStatus;

public class AuthorizationException extends Exception {

    private final HttpStatus status;
    private final String debug;

    public AuthorizationException(String message, HttpStatus status) {
        super(message);
        this.status = status;
        this.debug = null;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getLocalizedMessage() {
        if (debug == null) {
            return "";
        }
        return debug;
    }

}