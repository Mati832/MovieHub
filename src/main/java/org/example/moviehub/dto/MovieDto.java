package org.example.moviehub.dto;

import org.example.moviehub.model.Movie;

import java.util.Set;
import java.util.stream.Collectors;

public class MovieDto{
    private Long id;
    private String title;
    private String description;
    private Double rating;
    private Set<GenreDto> genres;
    private String director;
    private Integer year;
    private Set<MovieThumbnailDto> thumbnails;

    public MovieDto(Long id, String title, String description, Double rating, Set<GenreDto> genres, String director, Integer year, Set<MovieThumbnailDto> thumbnails) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.genres = genres;
        this.director = director;
        this.year = year;
        this.thumbnails = thumbnails;
    }

    public MovieDto(Movie movie){
        Set<MovieThumbnailDto> thumbnails = movie.getThumbnails().stream().map(thumb -> new MovieThumbnailDto(thumb)).collect(Collectors.toSet());
        Set<GenreDto> genres = movie.getGenres().stream().map(genre -> new GenreDto(genre)).collect(Collectors.toSet());
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.description = movie.getDescription();
        this.rating = movie.getRating();
        this.genres = genres;
        this.director = movie.getDirector();
        this.year = movie.getYear();
        this.thumbnails = thumbnails;
    }

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

    public Set<GenreDto> getGenres() {
        return genres;
    }

    public void setGenres(Set<GenreDto> genres) {
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

    public Set<MovieThumbnailDto> getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(Set<MovieThumbnailDto> thumbnails) {
        this.thumbnails = thumbnails;
    }
}
