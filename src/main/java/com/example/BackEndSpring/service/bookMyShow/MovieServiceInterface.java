package com.example.BackEndSpring.service.bookMyShow;

import com.example.BackEndSpring.dto.MovieDTO;
import com.example.BackEndSpring.dto.MovieRequest;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MovieServiceInterface {
    MovieDTO createMovie(MovieRequest movieRequest);

    // Retrieve movie details by ID; returns MovieDTO
    MovieDTO getMovieById(Long movieId);

    // Retrieve all movies; returns list of MovieDTOs
    List<MovieDTO> getAllMovies();

    // Update existing movie by ID; accepts MovieRequest, returns updated MovieDTO
    MovieDTO updateMovie(Long movieId, MovieRequest movieRequest);

    // Delete movie by ID; no return value
    void deleteMovie(Long movieId);
}
