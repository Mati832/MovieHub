package org.example.moviehub.service;

import org.example.moviehub.model.Genre;
import org.example.moviehub.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    public Genre createGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    public List<Genre> findAllGenres() {
        return genreRepository.findAll();
    }

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }
}
