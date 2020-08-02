package com.pantala.todo.tasks;

import com.pantala.todo.users.UserNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("tasks")
public class TasksController {

    private final TasksService tasksService;

    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @PostMapping
    Task createOne(@Valid @RequestBody Task task, HttpServletRequest request) throws UserNotFoundException {
        String userId = extractUserIdFromRequest(request);

        return tasksService.createOne(task, userId);
    }

    @GetMapping
    Iterable<Task> getUsersTasks (HttpServletRequest request) throws UserNotFoundException {
        String userId = extractUserIdFromRequest(request);

        return tasksService.getUsersTask(userId);
    }

    private String extractUserIdFromRequest(HttpServletRequest request){
        return (String) request.getAttribute("userId");
    }


}
