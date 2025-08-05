package org.example.moviehub.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.moviehub.model.Movie;
import org.example.moviehub.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(Long id, Movie movie) {
        Movie existingMovie = getMovieById(id).orElseThrow(() -> new EntityNotFoundException("Movie with id: " + id + " not found"));

        existingMovie.setTitle(movie.getTitle());
        existingMovie.setDescription(movie.getDescription());
        existingMovie.setRating(movie.getRating());
        existingMovie.setGenres(movie.getGenres());
        existingMovie.setDirector(movie.getDirector());
        existingMovie.setYear(movie.getYear());

        return movieRepository.save(existingMovie);
    }

    @Override
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}
