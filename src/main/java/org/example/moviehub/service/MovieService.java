package org.example.moviehub.service;

import org.example.moviehub.dto.MovieDto;
import org.example.moviehub.dto.MultipartFileDto;


import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<MovieDto> getAllMovies();

    Optional<MovieDto> getMovieById(Long id);

    MovieDto createMovie(MovieDto movie);

    MovieDto updateMovie(Long id, MovieDto movie);

    void deleteMovie(Long id);

    void deleteAllMovies();

    List<MovieDto> searchMoviesByTitle(String query);

    MovieDto addMovieThumbnail(Long movieId, MultipartFileDto file);
}
