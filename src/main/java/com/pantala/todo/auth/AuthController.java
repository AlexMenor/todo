package com.pantala.todo.auth;

import com.pantala.todo.auth.dto.LoginDto;
import com.pantala.todo.exceptions.UnauthorizedException;
import com.pantala.todo.users.UserNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("login")
    public String Login(@Valid @RequestBody LoginDto dto) throws UnauthorizedException, UserNotFoundException {
        return authService.login(dto.email, dto.password);
    }
}
