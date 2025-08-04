package org.example.moviehub.model;

import jakarta.persistence.*;
import org.example.moviehub.enums.Genre;

@Access(AccessType.FIELD)
@Entity
public class Movie{

    @Id @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String title;
    private String description;
    private double rating;

    @Enumerated(EnumType.STRING)
    private Genre genre;
    private String director;
    private int year;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}