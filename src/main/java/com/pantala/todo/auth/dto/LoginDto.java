package com.pantala.todo.auth.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class LoginDto {
    @Email
    public String email;
    @NotBlank
    public String password;
}
