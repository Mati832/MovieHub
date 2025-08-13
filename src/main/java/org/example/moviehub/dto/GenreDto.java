package org.example.moviehub.dto;

import org.example.moviehub.enums.GenreType;
import org.example.moviehub.model.Genre;

public record GenreDto(Long id,
                       GenreType genreType) {
    public GenreDto(Genre genre){
        this(genre.getId(),genre.getGenreType());
    }
}
