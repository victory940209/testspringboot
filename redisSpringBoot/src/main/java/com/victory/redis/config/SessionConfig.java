package com.victory.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableRedisHttpSession
@RequiredArgsConstructor
public class SessionConfig extends AbstractHttpSessionApplicationInitializer {

	@Bean
	public RedisConnectionFactory redisConnectionFactory() {

		return new LettuceConnectionFactory("192.168.0.103", 6379);
	}

	@Bean
	public RedisTemplate<?, ?> redisTemplate() {
		RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		return redisTemplate;
	}



}