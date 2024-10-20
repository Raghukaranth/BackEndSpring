package com.example.BackEndSpring.contorller;

import com.example.BackEndSpring.model.Payment;
import com.example.BackEndSpring.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    PaymentService paymentService;
    @PostMapping("/makepayment")
    public ResponseEntity<Payment> makePayment(@RequestBody Payment payment) {
        try {
            Payment paymentObj = paymentService.makePayment(payment);
            return new ResponseEntity<>(paymentObj, HttpStatus.CREATED);
        } catch (Exception e) { return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); }

    }
}
