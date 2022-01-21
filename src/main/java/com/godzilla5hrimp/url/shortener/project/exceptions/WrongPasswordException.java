package com.godzilla5hrimp.url.shortener.project.exceptions;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException(String errorMessage) {
        super(errorMessage);
    }
}
