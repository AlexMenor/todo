package com.pantala.todo.users.dto;


import javax.validation.constraints.NotBlank;

public class CreateOneUserDto {
    @NotBlank
    public String username;
    @NotBlank
    public String password;
}
