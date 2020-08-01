package com.pantala.todo.exceptions;

public class ExceptionResponse {
    private String message;
    private Object details;

    public ExceptionResponse(String message, Object details) {
        this.message = message;
        this.details = details;
    }

    public Object getDetails() {
        return details;
    }

    public void setDetails(Object details) {
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
