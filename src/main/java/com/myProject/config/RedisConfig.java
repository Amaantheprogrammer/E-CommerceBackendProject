package com.myProject.config;

import java.time.Duration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Configuration
@EnableCaching
public class RedisConfig {
    @Bean 
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        return RedisCacheManager.builder()
                .withCacheConfiguration(
                    "products",
                    RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(30))
                )
                .withCacheConfiguration(
                    "categories",
                    RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(12))
                )
                .withCacheConfiguration(
                    "users",
                    RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(10))
                )
                .build();
    }
}