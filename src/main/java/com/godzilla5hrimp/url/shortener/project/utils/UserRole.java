package com.godzilla5hrimp.url.shortener.project.utils;

public enum UserRole {
    USER("USER"),
    ADMIN("ADMIN");

    public final String label;

    UserRole(String label) {
        this.label = label;
    }
}
