package com.example.BackEndSpring.repository;

import com.example.BackEndSpring.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo,Long> {
}
