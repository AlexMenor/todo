package com.pantala.todo.tasks;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface TasksRepository extends PagingAndSortingRepository<Task, UUID> { }
