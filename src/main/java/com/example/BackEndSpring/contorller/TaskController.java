package com.example.BackEndSpring.contorller;

import com.example.BackEndSpring.model.TaskTracker;
import com.example.BackEndSpring.repository.TaskRepository;
import com.example.BackEndSpring.service.TaskTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TaskTrackerService taskTrackerService;


    @GetMapping("/getAllTasks")
    public ResponseEntity<List<TaskTracker>> getAllTasks() {
        try {
            return taskTrackerService.getAllTasks();
        } catch (Exception e) { return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); }
    }

    @PostMapping("/addTask")
    public ResponseEntity<TaskTracker> addTask(@RequestBody TaskTracker taskTracker) {
        try {
            TaskTracker taskTrackerObj = taskTrackerService.addTask(taskTracker);
            return new ResponseEntity<>(taskTrackerObj, HttpStatus.CREATED);
        } catch (Exception e) { return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); }
    }

    @DeleteMapping("/deleteTaskById/{id}")
    public ResponseEntity<HttpStatus> deleteTaskById(@PathVariable("id") Long id) {
        try {
            taskTrackerService.deleteTaskById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) { return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); }
    }
}