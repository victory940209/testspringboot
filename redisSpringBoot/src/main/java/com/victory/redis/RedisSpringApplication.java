package com.victory.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.FlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 600, flushMode = FlushMode.ON_SAVE, redisNamespace = "spring:session")
@SpringBootApplication
public class RedisSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisSpringApplication.class, args);
	}

}
