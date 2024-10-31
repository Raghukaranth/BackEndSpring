package com.example.BackEndSpring.contorller;

import com.example.BackEndSpring.model.Payment;
import com.example.BackEndSpring.service.PaymentService;
import jakarta.websocket.server.PathParam;
import org.apache.commons.logging.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private static final Logger log = LogManager.getLogger(PaymentController.class);
    @Autowired
    PaymentService paymentService;

    @PostMapping("/makepayment")
    public ResponseEntity<Payment> makePayment(@RequestBody Payment payment) {
        try {
            Payment paymentObj = paymentService.makePayment(payment);
            return new ResponseEntity<>(paymentObj, HttpStatus.CREATED);
        } catch (Exception e) { return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); }

    }

    @GetMapping("/status/{id}")
    public ResponseEntity<Payment> checkStatus(@PathVariable Long id) {
        try {
            Payment paymentObj = paymentService.checkStatus(id);
            log.info("Payment Successful");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.info("Payment Failed");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); }
    }
}
