package com.utma.todo_list.application.services;

import com.utma.todo_list.domain.Task;
import com.utma.todo_list.infraestructure.repositories.ITaskRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements ITaskService {

    @Autowired
    private ITaskRepository taskRepository;

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }


    @Override
    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow();
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task update(Long id, Task task) {
      Task originalTask = findById(id);
        BeanUtils.copyProperties(task,originalTask,"id");
        return taskRepository.save(originalTask);
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }
}
