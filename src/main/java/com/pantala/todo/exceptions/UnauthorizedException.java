package com.pantala.todo.exceptions;

public class UnauthorizedException extends Exception {
    private String details;

    public UnauthorizedException(String details) {
        super("Could not authenticate");
        this.details = details;
    }

    public String getDetails() {
        return details;
    }
}
