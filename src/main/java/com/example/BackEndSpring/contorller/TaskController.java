package com.example.BackEndSpring.contorller;

import com.example.BackEndSpring.model.TaskTracker;
import com.example.BackEndSpring.repository.TaskRepository;
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

    @GetMapping("/getAllTasks")
    public ResponseEntity<List<TaskTracker>> getAllTasks() {
        try {
            List<TaskTracker> taskTrackerList = new ArrayList<>();
            taskRepository.findAll().forEach(taskTrackerList::add);

            if (taskTrackerList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(taskTrackerList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addTask")
    public ResponseEntity<TaskTracker> addTask(@RequestBody TaskTracker taskTracker) {
        try {
            TaskTracker taskTrackerObj = taskRepository.save(taskTracker);
            return new ResponseEntity<>(taskTrackerObj, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}