package org.example.moviehub.dto;

import org.example.moviehub.model.MovieThumbnail;

public record MovieThumbnailDto(Long id,
                                String originalFilename,
                                String url,
                                String description
                               ) {
    public MovieThumbnailDto(MovieThumbnail movieThumbnail){
        this(movieThumbnail.getId(), movieThumbnail.getOriginalFileName(), movieThumbnail.getUrl(),movieThumbnail.getDescription());
    }
}
