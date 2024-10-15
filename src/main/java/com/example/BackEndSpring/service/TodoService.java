package com.example.BackEndSpring.service;

import com.example.BackEndSpring.model.Todo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TodoService {
    List<Todo> getAllTodos();
    Todo getTodoById(Long id) throws Exception;
    Todo createTodo(Todo todo);
    Todo updateTodo(Long id, Todo todo) throws Exception;
    void deleteTodo(Long id);

    Todo updateStatus(Long id) throws Exception;
}
