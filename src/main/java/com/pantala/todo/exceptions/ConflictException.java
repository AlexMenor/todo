package com.pantala.todo.exceptions;

public class ConflictException extends Exception{
    private String details;
    public ConflictException(String details) {
        super("An equally identified resource already exists");
        this.details = details;
    }

    public String getDetails() {
        return details;
    }
}
