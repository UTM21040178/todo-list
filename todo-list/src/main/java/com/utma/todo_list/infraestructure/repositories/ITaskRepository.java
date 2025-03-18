package com.utma.todo_list.infraestructure.repositories;

import com.utma.todo_list.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITaskRepository extends JpaRepository<Task, Long> {

}