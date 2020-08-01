package com.pantala.todo.users;

import com.pantala.todo.exceptions.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(String email) {
        super(String.format("Could not find an user with email %s", email));
    }
}
