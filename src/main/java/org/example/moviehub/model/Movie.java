package org.example.moviehub.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import org.example.moviehub.dto.MovieDto;


import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Access(AccessType.FIELD)
@Entity
public class Movie{

    @Id @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;
    private Double rating;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Genre> genres;
    private String director;
    private Integer year;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<MovieThumbnail> thumbnails = new HashSet<>();

    //constructors
    public Movie() {}

    public Movie(String title, String description, Double rating, Set<Genre>genres, String director, Integer year) {
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.genres = genres;
        this.director = director;
        this.year = year;
    }
    public Movie(MovieDto movieDto) {
        this(movieDto.getTitle(), movieDto.getDescription(), movieDto.getRating(), movieDto.getGenres().stream().map(genreDto->new Genre(genreDto)).collect(Collectors.toSet()),movieDto.getDirector(),movieDto.getYear());
    }

    //getter and setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Set<MovieThumbnail> getThumbnails() { return thumbnails; }

    public void addThumbnail(MovieThumbnail thumbnail) {
        this.thumbnails.add(thumbnail);
        thumbnail.setMovie(this);
    }
    public void removeThumbnail(MovieThumbnail thumbnail) {
        this.thumbnails.remove(thumbnail);
        thumbnail.setMovie(null);
    }
}