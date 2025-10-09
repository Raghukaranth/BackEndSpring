package com.example.BackEndSpring.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MovieRequest {
    private Long id;
    private String title;
    private String description;
    private int duration; // in minutes
    private String genre;
    private LocalDate releaseDate;
    private String language;
    private String certification;
}
