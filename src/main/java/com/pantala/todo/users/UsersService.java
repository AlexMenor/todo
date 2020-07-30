package com.pantala.todo.users;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {
    private final UsersRepository repository;

    public UsersService(UsersRepository repository){
        this.repository = repository;
    }

    public User createOne(User user) throws ConflictUserException{
        try {
            return this.repository.save(user);
        }
        catch(Exception exception){
           throw new ConflictUserException();
        }
    }
}

class ConflictUserException extends Exception{
    ConflictUserException(){
        super("Ya existe un usuario con este nombre de usuario");
    }

}
