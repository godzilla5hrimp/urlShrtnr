package com.godzilla5hrimp.url.shortener.project.exceptions;

public class LoginErrorException extends RuntimeException {
    public LoginErrorException(String errorMessage) {
        super(errorMessage);
    }
}
