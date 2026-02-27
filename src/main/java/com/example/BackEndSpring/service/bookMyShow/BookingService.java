package com.example.BackEndSpring.service.bookMyShow;

import com.example.BackEndSpring.model.bookMyShow.Booking;
import com.example.BackEndSpring.model.bookMyShow.PaymentStatus;
import com.example.BackEndSpring.model.bookMyShow.Seat;
import com.example.BackEndSpring.model.bookMyShow.SeatStatus;
import com.example.BackEndSpring.repository.bookMyShow.BookingRepository;
import com.example.BackEndSpring.repository.bookMyShow.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private SeatRepository seatRepository;

    // Create a booking with transactional integrity
    @Transactional
    public Booking createBooking(Long userId, List<Long> seatIds, Booking booking) {
        // Step 1: Lock the selected seats
        List<Seat> seats = seatRepository.findAllById(seatIds);
        for (Seat seat : seats) {
            if (seat.getStatus() != SeatStatus.AVAILABLE) {
                throw new RuntimeException("Seat " + seat.getId() + " is not available");
            }
            seat.setStatus(SeatStatus.LOCKED);
            seat.setLockedByUserId(userId);
            seatRepository.save(seat);
        }

        // Step 2: Create and save booking
        booking.setId(userId);
        booking.setSeatIds(seatIds);
        booking.setPaymentStatus(PaymentStatus.UNPAID);
        Booking savedBooking = bookingRepository.save(booking);

        // Transaction ensures both seat updates and booking creation succeed/fail together
        return savedBooking;
    }

    // Confirm payment and complete booking
    @Transactional
    public Booking confirmPayment(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        List<Seat> seats = seatRepository.findAllById(booking.getSeatIds());
        for (Seat seat : seats) {
            if (seat.getStatus() != SeatStatus.LOCKED) {
                throw new RuntimeException("Seat " + seat.getId() + " is not locked");
            }
            seat.setStatus(SeatStatus.BOOKED);
            seat.setLockedByUserId(null);
            seatRepository.save(seat);
        }

        booking.setPaymentStatus(PaymentStatus.PAID);
        return bookingRepository.save(booking);
    }

    // Cancel booking and release seats
    @Transactional
    public void cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        List<Seat> seats = seatRepository.findAllById(booking.getSeatIds());
        for (Seat seat : seats) {
            if (seat.getStatus() == SeatStatus.LOCKED) {
                seat.setStatus(SeatStatus.AVAILABLE);
                seat.setLockedByUserId(null);
                seatRepository.save(seat);
            }
        }

        bookingRepository.deleteById(bookingId);
    }
}
