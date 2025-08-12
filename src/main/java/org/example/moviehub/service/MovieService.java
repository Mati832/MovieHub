package org.example.moviehub.service;

import org.example.moviehub.model.Movie;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<Movie> getAllMovies();

    Optional<Movie> getMovieById(Long id);

    Movie createMovie(Movie movie);

    Movie updateMovie(Long id, Movie movie);

    void deleteMovie(Long id);

    void deleteAllMovies();

    List<Movie> searchMoviesByTitle(String query);
}
