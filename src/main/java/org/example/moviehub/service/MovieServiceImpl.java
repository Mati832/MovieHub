package org.example.moviehub.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.moviehub.dto.MovieDto;
import org.example.moviehub.dto.MultipartFileDto;
import org.example.moviehub.exceptions.FileStorageException;
import org.example.moviehub.exceptions.MovieNotFoundException;
import org.example.moviehub.model.Movie;
import org.example.moviehub.model.MovieThumbnail;
import org.example.moviehub.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final String movieThumbnailPath;

    public MovieServiceImpl(MovieRepository movieRepository, @Value("${moviehub.resources.thumbnails.uploads.path}") String movieThumbnailPath) {
        this.movieRepository = movieRepository;
        this.movieThumbnailPath = movieThumbnailPath;
    }

    @Override
    public List<MovieDto> getAllMovies() {
        return movieRepository.findAll().stream().map(MovieDto::new).collect(Collectors.toList());
    }

    @Override
    public Optional<MovieDto> getMovieById(Long id) {
        return movieRepository.findById(id).map(MovieDto::new);
    }

    @Override
    public MovieDto createMovie(MovieDto movie) {
        return new MovieDto(movieRepository.save(new Movie(movie)));
    }

    @Override
    public MovieDto updateMovie(Long id, MovieDto movie) {
        MovieDto existingMovie = getMovieById(id).orElseThrow(() -> new EntityNotFoundException("Movie with id: " + id + " not found"));

        existingMovie.setTitle(movie.getTitle());
        existingMovie.setDescription(movie.getDescription());
        existingMovie.setRating(movie.getRating());
        existingMovie.setGenres(movie.getGenres());
        existingMovie.setDirector(movie.getDirector());
        existingMovie.setYear(movie.getYear());

        return new MovieDto(movieRepository.save(new Movie(existingMovie)));
    }

    @Override
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public void deleteAllMovies() {
        movieRepository.deleteAll();
    }


    @Cacheable(cacheNames = "searchCache", key = "#query")
    public List<MovieDto> searchMoviesByTitle(String query) {
        return movieRepository.searchMoviesByTitle(query).stream().map(MovieDto::new).collect(Collectors.toList());
    }

    @Override
    public MovieDto addMovieThumbnail(Long movieId, MultipartFileDto fileDto) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException("Movie with id: " + movieId + " not found"));
        MultipartFile file = fileDto.file();

        String datePath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        Path targetDir = Paths.get(movieThumbnailPath, datePath);
        try {
            Files.createDirectories(targetDir);
        } catch (IOException e) {
            throw new FileStorageException("Failed to create directories for path: " + targetDir, e);
        }
        String extension = Optional.ofNullable(file.getOriginalFilename())
                .filter(f -> f.contains("."))
                .map(f -> f.substring(f.lastIndexOf(".")))
                .orElse("");
        String uniqueFileName = UUID.randomUUID().toString() + extension;
        Path absoluteTargetPath = targetDir.resolve(uniqueFileName);

        try {
            Files.copy(file.getInputStream(), absoluteTargetPath);
        } catch (IOException e) {
            throw new FileStorageException("Failed to store image into file: " + file.getOriginalFilename(), e);
        }
        MovieThumbnail movieThumbnail = new MovieThumbnail(file.getOriginalFilename(), fileDto.description(), absoluteTargetPath.toString(), file.getSize(), LocalDateTime.now(), file.getContentType(), movie);
        movie.addThumbnail(movieThumbnail);
        return new MovieDto(movieRepository.save(movie));
    }
}
