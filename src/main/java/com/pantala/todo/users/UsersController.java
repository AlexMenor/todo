package com.pantala.todo.users;

import com.pantala.todo.users.dto.CreateOneUserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("users")
public class UsersController {

    private final UsersService service;

    public UsersController(UsersService service) {
        this.service = service;
    }

    @PostMapping
    ResponseEntity<User> createOne(@Valid @RequestBody() CreateOneUserDto dto) {
        User user = new User();
        user.setUsername(dto.username);
        user.setPassword(dto.password);

        try {
            return new ResponseEntity<>(this.service.createOne(user), HttpStatus.CREATED);
        }
        catch (ConflictUserException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
