package com.utma.todo_list.application.services;

import com.utma.todo_list.domain.Task;

import java.util.List;
import java.util.Optional;

public interface ITaskService {
    List<Task> findAll();
    Task findById(Long id);
    Task save(Task task);
    Task update(Long id, Task task);
    void deleteById(Long id);
}