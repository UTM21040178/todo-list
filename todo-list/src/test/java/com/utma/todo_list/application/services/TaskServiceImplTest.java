package com.utma.todo_list.application.services;

import com.utma.todo_list.domain.Task;
import com.utma.todo_list.infraestructure.repositories.ITaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceImplTest {

    @Mock
    private ITaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_ShouldReturnTaskList() {
        List<Task> tasks = List.of(new Task(1L, "Test Task", "Description", false, 1L));
        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> result = taskService.findAll();

        assertEquals(tasks, result);
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    void findById_ShouldReturnTask() {
        Task task = new Task(1L, "Test Task", "Description", false, 1L);
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Task result = taskService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(taskRepository, times(1)).findById(1L);
    }

    @Test
    void save_ShouldReturnSavedTask() {
        Task task = new Task(null, "New Task", "Description", false, 1L);
        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskService.save(task);

        assertEquals(task, result);
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void update_ShouldReturnUpdatedTask() {
        Task task = new Task(1L, "Updated Task", "Description", true, 1L);
        when(taskRepository.findById(anyLong())).thenReturn(Optional.of(task));
        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskService.update(1L, task);

        assertEquals(task, result);
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void deleteById_ShouldRemoveTask() {
        doNothing().when(taskRepository).deleteById(1L);

        taskService.deleteById(1L);

        verify(taskRepository, times(1)).deleteById(1L);
    }
}
