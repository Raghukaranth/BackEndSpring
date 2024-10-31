package com.example.BackEndSpring.repository;

import com.example.BackEndSpring.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository <Payment, Long> {
}
