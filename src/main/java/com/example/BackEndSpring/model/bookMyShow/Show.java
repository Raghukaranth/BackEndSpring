package com.example.BackEndSpring.model.bookMyShow;

import com.example.BackEndSpring.dto.MovieDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "shows")
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;

    @ManyToOne
    @JoinColumn(name = "screen_id")
    private Screen screen;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private double price;
}
