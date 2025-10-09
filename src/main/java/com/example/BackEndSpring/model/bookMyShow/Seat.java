package com.example.BackEndSpring.model.bookMyShow;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String seatNumber;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    private boolean isBooked;
    private SeatStatus status;
    private Long lockedByUserId;

    @ManyToOne
    @JoinColumn(name = "screen_id")
    private Screen screen;
}
