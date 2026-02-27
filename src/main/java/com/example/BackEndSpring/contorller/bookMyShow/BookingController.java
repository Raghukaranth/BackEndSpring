package com.example.BackEndSpring.contorller.bookMyShow;

import com.example.BackEndSpring.model.bookMyShow.Booking;
import com.example.BackEndSpring.service.bookMyShow.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mybms/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping("/create")
    public ResponseEntity<Booking> createBooking(
            @RequestParam Long userId,
            @RequestBody List<Long> seatIds,
            @RequestBody Booking booking) {
        Booking saveBooking = bookingService.createBooking(userId, seatIds, booking);
        return ResponseEntity.ok(saveBooking);
    }

    @PutMapping("/confirm/{bookingId}")
    public ResponseEntity<Booking> confirmBooking(@PathVariable Long bookingiId) {
        Booking confirmBooking = bookingService.confirmPayment(bookingiId);
        return ResponseEntity.ok(confirmBooking);
    }

    @DeleteMapping("/cancel/{bookingId}")
    public ResponseEntity<Booking> cancelBooking(@PathVariable Long bookingId) {
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.noContent().build();
    }


}
