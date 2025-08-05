package org.example.moviehub.repository;

import org.example.moviehub.enums.GenreType;
import org.example.moviehub.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre,Long> {
    Optional<Genre> findGenreByGenreType(GenreType genreType);
}
