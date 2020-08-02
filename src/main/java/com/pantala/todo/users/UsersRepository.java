package com.pantala.todo.users;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsersRepository extends PagingAndSortingRepository<User, UUID> {
    Optional<User> findByEmail(String email);
}
