package com.example.BackEndSpring.repository;

import com.example.BackEndSpring.model.ExpenseTracker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<ExpenseTracker, Long> {
}
