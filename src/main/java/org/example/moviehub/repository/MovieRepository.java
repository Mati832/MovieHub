package org.example.moviehub.repository;

import org.example.moviehub.enums.Genre;
import org.example.moviehub.model.Movie;

import java.util.List;

public interface MovieRepository {
    List<Movie> findByTitle(String title);
    List<Movie> findByGenre(Genre genre);
    List<Movie> findByRatingGreaterThan(double rating);
    List<Movie> findByGenreAndYear(Genre genre, int year);
    List<Movie> findByTitleContainingIgnoreCase(String keyword);
    List<Movie> findTop5ByYearOrderByRatingDesc(int year);
}
