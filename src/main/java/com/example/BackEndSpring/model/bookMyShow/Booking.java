package com.example.BackEndSpring.model.bookMyShow;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Link to the user making this booking
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Link to the show this booking is for
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    // The booked seats
    @ManyToMany
    @JoinTable(name = "booking_seats",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "seat_id"))
    private List<Seat> seats;
    private List<Long> seatIds;

    private double totalPrice;

    private LocalDateTime bookingTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus; // e.g. PAID, UNPAID, PENDING

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus status; // e.g. CONFIRMED, CANCELLED, FAILED

    @PrePersist
    protected void onCreate() {
        this.bookingTime = LocalDateTime.now();
    }



    // Lombok @Getter/@Setter will generate standard getters/setters
}
