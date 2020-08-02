package com.pantala.todo.tasks;

import com.pantala.todo.users.User;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Task {
    public Task(){}

    @Id
    private UUID id;

    @NotBlank
    private String description;

    @NonNull
    private Boolean completed = false;


    @NonNull
    public Boolean getCompleted() {
        return completed;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    User user;

    public void setCompleted(@NonNull Boolean completed) {
        this.completed = completed;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
