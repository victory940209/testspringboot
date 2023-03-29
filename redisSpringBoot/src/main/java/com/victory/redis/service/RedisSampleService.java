package com.victory.redis.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RedisSampleService {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	public String getRedisStringValue(String key) {
		ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
		log.info("Redis key : " + key);

		return stringValueOperations.get(key);
	}

	public void setRedisStringValue(Map<String, Object> param) {
		ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();

		for (Map.Entry<String, Object> entry : param.entrySet())
		{
			log.info(entry.getKey() + " : " + entry.getValue().toString());
			stringValueOperations.set(entry.getKey(), entry.getValue().toString());
		}


	}

}
