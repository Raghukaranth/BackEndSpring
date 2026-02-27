package com.example.BackEndSpring.dto;

import com.example.BackEndSpring.model.bookMyShow.Movie;

public class MovieMapper {
    public static MovieDTO toDTO(Movie movie) {
        if(movie == null) return null;

        MovieDTO dto = new MovieDTO();
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setDescription(movie.getDescription());
        dto.setDuration(movie.getDurationInMinutes());
        dto.setGenre(movie.getGenre());
        dto.setReleaseDate(movie.getReleaseDate());
        dto.setLanguage(movie.getLanguage());
        dto.setCertification(movie.getCertificate());
        return dto;
    }

    public static Movie toEntity(MovieRequest movieRequest) {
        if(movieRequest == null) return null;

        Movie movie = new Movie();

        movie.setTitle(movieRequest.getTitle());
        movie.setDescription(movieRequest.getDescription());
        movie.setDurationInMinutes(movieRequest.getDuration());
        movie.setGenre(movieRequest.getGenre());
        movie.setReleaseDate(movieRequest.getReleaseDate());
        movie.setLanguage(movieRequest.getLanguage());
        movie.setCertificate(movieRequest.getCertification());
        return movie;
    }

    public static void updateEntity(Movie movie, MovieRequest movieRequest) {
        if (movie == null || movieRequest == null) return;

        movie.setTitle(movieRequest.getTitle());
        movie.setDescription(movieRequest.getDescription());
        movie.setDurationInMinutes(movieRequest.getDuration());
        movie.setGenre(movieRequest.getGenre());
        movie.setReleaseDate(movieRequest.getReleaseDate());
        movie.setLanguage(movieRequest.getLanguage());
        movie.setCertificate(movieRequest.getCertification());
    }
}
