package com.example.BackEndSpring.controller;

import com.example.BackEndSpring.contorller.TaskController;
import com.example.BackEndSpring.model.TaskTracker;
import com.example.BackEndSpring.repository.TaskRepository;
import com.example.BackEndSpring.service.TaskTrackerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
@WithMockUser
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TaskTrackerService taskTrackerService;

    @MockBean
    private TaskRepository taskRepository;  // Add this to mock the repository

    private TaskTracker taskTracker1;
    private TaskTracker taskTracker2;

    @BeforeEach
    void setUp() {
        taskTracker1 = new TaskTracker(1L, "Complete project documentation");
        taskTracker2 = new TaskTracker(2L, "Review pull requests");
    }

    @Test
    void getAllTasks_ShouldReturnListOfTasks() throws Exception {
        // Arrange
        List<TaskTracker> tasks = Arrays.asList(taskTracker1, taskTracker2);
        when(taskTrackerService.getAllTasks()).thenReturn(new ResponseEntity<>(tasks, HttpStatus.OK));

        // Act & Assert
        mockMvc.perform(get("/task/getAllTasks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].task").value("Complete project documentation"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].task").value("Review pull requests"));

        verify(taskTrackerService, times(1)).getAllTasks();
    }

    @Test
    void getAllTasks_WhenNoContent_ShouldReturnNoContent() throws Exception {
        // Arrange
        when(taskTrackerService.getAllTasks()).thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));

        // Act & Assert
        mockMvc.perform(get("/task/getAllTasks"))
                .andExpect(status().isNoContent());

        verify(taskTrackerService, times(1)).getAllTasks();
    }

    @Test
    void getAllTasks_WhenServiceThrowsException_ShouldReturnInternalServerError() throws Exception {
        // Arrange
        when(taskTrackerService.getAllTasks()).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        mockMvc.perform(get("/task/getAllTasks"))
                .andExpect(status().isInternalServerError());

        verify(taskTrackerService, times(1)).getAllTasks();
    }

    @Test
    void addTask_ShouldCreateTaskAndReturnCreatedStatus() throws Exception {
        // Arrange
        TaskTracker newTask = new TaskTracker(0L, "Write unit tests");
        TaskTracker savedTask = new TaskTracker(3L, "Write unit tests");
        when(taskTrackerService.addTask(any(TaskTracker.class))).thenReturn(savedTask);

        // Act & Assert
        mockMvc.perform(post("/task/addTask")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newTask)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.task").value("Write unit tests"));

        verify(taskTrackerService, times(1)).addTask(any(TaskTracker.class));
    }

    @Test
    void addTask_WhenServiceThrowsException_ShouldReturnInternalServerError() throws Exception {
        // Arrange
        TaskTracker newTask = new TaskTracker(0L, "Write unit tests");
        when(taskTrackerService.addTask(any(TaskTracker.class))).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        mockMvc.perform(post("/task/addTask")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newTask)))
                .andExpect(status().isInternalServerError());

        verify(taskTrackerService, times(1)).addTask(any(TaskTracker.class));
    }

    @Test
    void deleteTaskById_ShouldDeleteTaskAndReturnOk() throws Exception {
        // Arrange
        doNothing().when(taskTrackerService).deleteTaskById(1L);

        // Act & Assert
        mockMvc.perform(delete("/task/deleteTaskById/1")
                        .with(csrf()))
                .andExpect(status().isOk());

        verify(taskTrackerService, times(1)).deleteTaskById(1L);
    }

    @Test
    void deleteTaskById_WhenServiceThrowsException_ShouldReturnInternalServerError() throws Exception {
        // Arrange
        doThrow(new RuntimeException("Task not found")).when(taskTrackerService).deleteTaskById(anyLong());

        // Act & Assert
        mockMvc.perform(delete("/task/deleteTaskById/999")
                        .with(csrf()))
                .andExpect(status().isInternalServerError());

        verify(taskTrackerService, times(1)).deleteTaskById(999L);
    }
}