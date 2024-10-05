package com.example.BackEndSpring.repository;

import com.example.BackEndSpring.model.TaskTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskTracker, Long> {
}
