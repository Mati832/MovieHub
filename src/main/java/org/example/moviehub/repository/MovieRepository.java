package org.example.moviehub.repository;

import org.example.moviehub.enums.Genre;
import org.example.moviehub.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Long> {
    List<Movie> findByTitle(String title);
    List<Movie> findByGenre(Genre genre);
    List<Movie> findByRatingGreaterThan(double rating);
    List<Movie> findByGenreAndYear(Genre genre, int year);
    List<Movie> findByTitleContainingIgnoreCase(String keyword);
    List<Movie> findTop5ByYearOrderByRatingDesc(int year);

    Movie existsAllByDescription(String description);
}
