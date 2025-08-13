package org.example.moviehub.controller;

import org.example.moviehub.dto.MovieDto;
import org.example.moviehub.dto.MultipartFileDto;
import org.example.moviehub.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieDto> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public MovieDto getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie with id " + id + " not found"));
    }

    @PostMapping
    public MovieDto createMovie(@RequestBody MovieDto movie) {
        return movieService.createMovie(movie);
    }

    @PutMapping("/{id}")
    public MovieDto updateMovie(@PathVariable Long id, @RequestBody MovieDto movie) {
        return movieService.updateMovie(id, movie);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }

    @GetMapping("/search")
    public List<MovieDto> searchMoviesByTitle(@RequestParam("query") String query) {
        return movieService.searchMoviesByTitle(query);
    }

    @PostMapping("/upload/thumbnail")
    public MovieDto uploadMovieThumbnail(
            @RequestParam("id") Long id,
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "description", required = false) String description) {

        return movieService.addMovieThumbnail(id, new MultipartFileDto(file, description));
    }
}
