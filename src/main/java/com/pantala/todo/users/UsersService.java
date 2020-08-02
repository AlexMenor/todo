package com.pantala.todo.users;

import com.pantala.todo.exceptions.ConflictException;
import com.pantala.todo.exceptions.NotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsersService {
    private final UsersRepository repository;
    private final PasswordEncoder encoder;


    public UsersService(UsersRepository repository, PasswordEncoder encoder){
        this.repository = repository;
        this.encoder = encoder;
    }

    public User createOne(User user) throws UserConflictException {
        try {
            String encryptedPassword = this.encoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);

            user.setId(UUID.randomUUID());

            return this.repository.save(user);
        }
        catch(Exception exception){
           throw new UserConflictException(user.getEmail());
        }
    }

    public User getOne(String id) throws UserNotFoundException {
        Optional<User> userOptional = repository.findById(UUID.fromString(id));
        if (!userOptional.isPresent())
            throw new UserNotFoundException();

        return userOptional.get();
    }
}

class UserConflictException extends ConflictException{
    UserConflictException(String email){
        super(String.format("An user with email %s already exists", email));
    }
}

