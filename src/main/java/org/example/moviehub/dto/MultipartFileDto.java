package org.example.moviehub.dto;

import org.springframework.web.multipart.MultipartFile;

public record MultipartFileDto(MultipartFile file,
                               String description) {
}
