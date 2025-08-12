package org.example.moviehub;

import org.example.moviehub.service.MovieServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.slf4j.Logger;

import java.util.Scanner;

@EnableCaching
@SpringBootApplication
public class MovieHubApplication {

	private static final Logger logger = LoggerFactory.getLogger(MovieHubApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(MovieHubApplication.class, args);

	}
}
