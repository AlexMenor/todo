package com.pantala.todo.users;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface UsersRepository extends PagingAndSortingRepository<User, Integer> {

}
