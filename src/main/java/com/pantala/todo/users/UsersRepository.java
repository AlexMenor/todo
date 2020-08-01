package com.pantala.todo.users;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UsersRepository extends PagingAndSortingRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
