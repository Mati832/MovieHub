package org.example.moviehub.service;

import org.example.moviehub.dto.MovieDto;
import org.example.moviehub.model.Movie;
import org.example.moviehub.repository.MovieRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MovieServiceImplTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImpl movieServiceImpl;


    @Test
    void testCreateMovie() {
        Movie movie = new Movie();
        movie.setTitle("title");
        try {
            Mockito.when(movieRepository.save(Mockito.any(Movie.class))).thenReturn(movie);
            Movie created = new Movie(movieServiceImpl.createMovie(new MovieDto(movie)));
            Assertions.assertEquals("title", created.getTitle());
            Mockito.verify(movieRepository, Mockito.times(1)).save(movie);
        } catch (Exception e) {
            Assertions.fail();
        }
    }
}
