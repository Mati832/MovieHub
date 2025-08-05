package org.example.moviehub.model;

import jakarta.persistence.*;


import java.util.Set;

@Access(AccessType.FIELD)
@Entity
public class Movie{

    @Id @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;
    private Double rating;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Genre> genres;
    private String director;
    private Integer year;

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

}