package com.pantala.todo.auth;

import com.pantala.todo.exceptions.UnauthorizedException;
import com.pantala.todo.users.User;
import com.pantala.todo.users.UserNotFoundException;
import com.pantala.todo.users.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UsersRepository usersRepository;

    public AuthService(JwtService jwtService, PasswordEncoder passwordEncoder, UsersRepository usersRepository) {
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.usersRepository = usersRepository;
    }

    public String login(String email, String password)throws UserNotFoundException, UnauthorizedException {
        Optional<User> user = usersRepository.findByEmail(email);

        if(!user.isPresent())
            throw new UserNotFoundException(email);

        if (!passwordEncoder.matches(password, user.get().getPassword()))
            throw new UnauthorizedException("Password is not correct");

        return jwtService.generateJwt(user.get().getId().toString());

    }
}
