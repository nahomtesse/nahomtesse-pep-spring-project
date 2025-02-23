package com.example.exception;

import org.springframework.http.HttpStatus;

public class SocialMediaException extends RuntimeException {
    private final HttpStatus status;

    public SocialMediaException(HttpStatus status) {
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}


