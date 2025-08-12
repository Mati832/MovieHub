package org.example.moviehub.repository;


import org.example.moviehub.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Long> {
    List<Movie> findByTitle(String title);
    List<Movie> findByRatingGreaterThan(double rating);
    List<Movie> findByTitleContainingIgnoreCase(String keyword);
    List<Movie> findTop5ByYearOrderByRatingDesc(int year);

    @Query(value = """
            SELECT *
            FROM movie
            WHERE title LIKE CONCAT(:query, '%')
               OR MATCH(title) AGAINST (CONCAT(:query, '*') IN BOOLEAN MODE)
            ORDER BY MATCH(title) AGAINST (:query IN BOOLEAN MODE) DESC, title ASC
            LIMIT 10;
            """, nativeQuery = true)
    List<Movie> searchMoviesByTitle(@Param("query") String query);
}
