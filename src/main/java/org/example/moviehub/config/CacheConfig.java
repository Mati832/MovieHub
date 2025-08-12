package org.example.moviehub.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CacheProperties.class)
public class CacheConfig {


    @Bean
    public CaffeineCacheManager caffeineCacheManager(org.example.moviehub.Properties.CacheProperties cacheProperties) {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();

        cacheManager.setCaffeine(Caffeine.newBuilder()
                .maximumSize(cacheProperties.getDefaultMaximumSize())
                .expireAfterWrite(cacheProperties.getDefaultExpireAfterWrite()));

        cacheProperties.getCaches().forEach((__, cache) -> {
            cacheManager.registerCustomCache(cache.getName(),Caffeine.newBuilder().maximumSize(cache.getMaximumSize()).expireAfterWrite(cache.getExpireAfterWrite()).build());
        });

        return cacheManager;
    }
}
