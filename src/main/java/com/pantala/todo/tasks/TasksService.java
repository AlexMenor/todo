package com.pantala.todo.tasks;

import com.pantala.todo.users.User;
import com.pantala.todo.users.UserNotFoundException;
import com.pantala.todo.users.UsersService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class TasksService {

    private final TasksRepository repository;
    private final UsersService usersService;

    public TasksService(TasksRepository repository, UsersService usersService) {
        this.repository = repository;
        this.usersService = usersService;
    }


    public Task createOne(Task task, String userId) throws UserNotFoundException {
        User user = usersService.getOne(userId);

        task.setId(UUID.randomUUID());

        task.setUser(user);

        repository.save(task);

        return task;
    }

    public Iterable<Task> getUsersTask(String userId) throws UserNotFoundException {

        User user = usersService.getOne(userId);

        return user.getTasks();
    }




}
