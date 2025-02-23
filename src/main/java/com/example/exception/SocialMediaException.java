package com.example.exception;

import org.springframework.http.HttpStatus;

public class SocialMediaException extends RuntimeException {
    private HttpStatus status;

    public SocialMediaException(HttpStatus status) {
        super();
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
