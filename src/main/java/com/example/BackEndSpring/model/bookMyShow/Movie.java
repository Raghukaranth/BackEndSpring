package com.example.BackEndSpring.model.bookMyShow;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NonNull
    @NotBlank
    private String title;

    @Column(nullable = false)
    @NotNull
    @NotBlank
    private String genre;

    @Column(nullable = false)
    @NotNull
    @NotBlank
    private String language;

    @Column(nullable = false)
    @NotNull
    private int durationInMinutes;

    private String description;

    @Column(nullable = false)
    @NotNull
    private LocalDate releaseDate;

    @Column(nullable = false)
    @NotNull
    @NotBlank
    private String certificate;
}
