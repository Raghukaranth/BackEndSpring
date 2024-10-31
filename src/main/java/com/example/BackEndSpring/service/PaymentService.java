package com.example.BackEndSpring.service;

import com.example.BackEndSpring.model.Payment;
import com.example.BackEndSpring.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    public Payment makePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment checkStatus(Long id) throws Exception {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new Exception("Payment not matching with " + id));
    }
}
