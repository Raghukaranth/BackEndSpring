package com.example.BackEndSpring.service.bookMyShow;

import com.example.BackEndSpring.dto.MovieDTO;
import com.example.BackEndSpring.dto.MovieMapper;
import com.example.BackEndSpring.dto.MovieRequest;
import com.example.BackEndSpring.model.bookMyShow.Movie;
import com.example.BackEndSpring.repository.bookMyShow.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService implements MovieServiceInterface {
    @Autowired
    MovieRepository movieRepository;

    @Override
    public MovieDTO createMovie(MovieRequest movieRequest) {
        Movie movie = MovieMapper.toEntity(movieRequest);
        Movie savedMovie = movieRepository.save(movie);
        return MovieMapper.toDTO(savedMovie);
    }

    @Override
    public MovieDTO getMovieById(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie Not Found"));
        return MovieMapper.toDTO(movie);
    }

    @Override
    public List<MovieDTO> getAllMovies() {
        return movieRepository.findAll()
                .stream()
                .map(MovieMapper :: toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MovieDTO updateMovie(Long movieId, MovieRequest movieRequest) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie Not Found"));
        MovieMapper.updateEntity(movie, movieRequest);
        Movie updateMovie = movieRepository.save(movie);
        return MovieMapper.toDTO(updateMovie);
    }

    @Override
    public void deleteMovie(Long movieId) {
        movieRepository.deleteById(movieId);
    }
}
