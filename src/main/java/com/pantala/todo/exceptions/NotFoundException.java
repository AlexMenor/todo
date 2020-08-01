package com.pantala.todo.exceptions;

public class NotFoundException extends Exception{
    private String details;
    public NotFoundException(String details) {
        super("Could not find the resource");
        this.details = details;
    }

    public String getDetails() {
        return details;
    }
}
