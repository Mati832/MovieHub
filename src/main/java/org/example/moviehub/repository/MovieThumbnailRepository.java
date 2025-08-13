package org.example.moviehub.repository;

import org.example.moviehub.model.MovieThumbnail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieThumbnailRepository extends JpaRepository<MovieThumbnail, Long> {
    List<MovieThumbnail> findByMovieId(Long movieId);

    List<MovieThumbnail> findByContentType(String contentType);
}
