package com.example.BackEndSpring.service;

import com.example.BackEndSpring.model.TaskTracker;
import com.example.BackEndSpring.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskTrackerService {

    @Autowired
    TaskRepository taskRepository;

    public ResponseEntity<List<TaskTracker>> getAllTasks() {
        List<TaskTracker> taskTrackerList = new ArrayList<>();
        taskRepository.findAll().forEach(taskTrackerList::add);
        if (taskTrackerList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(taskTrackerList, HttpStatus.OK);

    }

    public TaskTracker addTask(TaskTracker taskTracker) {
        taskRepository.save(taskTracker);
        return taskTracker;
    }

    public boolean deleteTaskById(long id) {
        if (!taskRepository.existsById(id)) {
            return false;
        }
        taskRepository.deleteById(id);
        return true;
    }

}
