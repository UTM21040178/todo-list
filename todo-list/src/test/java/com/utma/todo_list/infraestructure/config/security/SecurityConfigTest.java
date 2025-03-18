package com.utma.todo_list.infraestructure.config.security;

import com.utma.todo_list.application.services.ITaskService;
import com.utma.todo_list.domain.Task;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SecurityConfigTest {

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @BeforeAll
    static void setUp() {
        postgres.start();
        System.setProperty("spring.datasource.url", postgres.getJdbcUrl());
        System.setProperty("spring.datasource.username", postgres.getUsername());
        System.setProperty("spring.datasource.password", postgres.getPassword());
    }

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ITaskService taskService;

    @Test
    void shouldAllowAccessToGetAllTasks() throws Exception {
        mockMvc.perform(get("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldAllowAccessToGetTaskById() throws Exception {
        Task task = new Task(1L, "Test Task", "Description", false, 1L);
        when(taskService.findById(1L)).thenReturn(task);

        mockMvc.perform(get("/api/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldAllowAccessToCreateTask() throws Exception {
        Task task = new Task(null, "New Task", "Description", false, 1L);
        when(taskService.save(any(Task.class))).thenReturn(task);

        String taskJson = "{\"title\":\"New Task\", \"description\":\"Description\", \"completed\":false, \"userId\":1}";

        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(taskJson))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldAllowAccessToUpdateTask() throws Exception {
        Task task = new Task(1L, "Updated Task", "Updated Description", true, 1L);
        when(taskService.update(eq(1L), any(Task.class))).thenReturn(task);

        String taskJson = "{\"title\":\"Updated Task\", \"description\":\"Updated Description\", \"completed\":true, \"userId\":1}";

        mockMvc.perform(put("/api/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(taskJson))
                .andExpect(status().isOk());
    }

    @Test
    void shouldAllowAccessToDeleteTask() throws Exception {
        doNothing().when(taskService).deleteById(1L);

        mockMvc.perform(delete("/api/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
