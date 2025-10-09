package com.example.BackEndSpring.contorller.bookMyShow;

import com.example.BackEndSpring.dto.MovieDTO;
import com.example.BackEndSpring.dto.MovieRequest;
import com.example.BackEndSpring.service.bookMyShow.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mybms")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieDTO> createMovie(@RequestBody MovieRequest movieRequest) {
        MovieDTO createdMovie = movieService.createMovie(movieRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMovie);
    }

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getAllMovies() {
        List<MovieDTO> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    // Read One: Return 200 OK with MovieDTO, or 404 if not found
    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable Long id) {
        MovieDTO movie = movieService.getMovieById(id);
        if (movie == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(movie);
    }

    // Update: Return 200 OK with updated MovieDTO
    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> updateMovie(@PathVariable Long id, @RequestBody MovieRequest movieRequest) {
        MovieDTO updatedMovie = movieService.updateMovie(id, movieRequest);
        return ResponseEntity.ok(updatedMovie);
    }

    // Delete: Return 204 No Content on successful deletion
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}
