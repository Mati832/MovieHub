package org.example.moviehub.model;

import jakarta.persistence.*;
import org.example.moviehub.dto.GenreDto;
import org.example.moviehub.enums.GenreType;

@Access(AccessType.FIELD)
@Entity
public class Genre {
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private GenreType genreType;

    //constructor
    public Genre(GenreType genreType) {
        this.genreType = genreType;
    }
    public Genre(GenreDto genreDto) {
        this.genreType=genreDto.genreType();
        this.id = genreDto.id();
    }

    public Genre() {}

    //getter and Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GenreType getGenreType() {
        return genreType;
    }

    public void setGenreType(GenreType genreType) {
        this.genreType = genreType;
    }
}
