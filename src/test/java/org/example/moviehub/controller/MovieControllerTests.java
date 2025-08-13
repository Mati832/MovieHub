package org.example.moviehub.controller;

import org.example.moviehub.dto.MovieDto;
import org.example.moviehub.model.Movie;
import org.example.moviehub.service.MovieService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MovieService movieService;

    @Test
    void testGetMovieById() throws Exception {
        Long id = 1L;
        Movie movie = new Movie();
        movie.setId(id);
        Mockito.when(movieService.getMovieById(id)).thenReturn(Optional.of(new MovieDto(movie)));

        mockMvc.perform(get("/api/movies/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id));
    }

    @Test
    void testUploadMovieThumbnail() throws Exception {

    }
}
