package com.victory.redis.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.victory.redis.service.RedisSampleService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RedisSampleController {

	@Autowired
	private RedisSampleService redisSampleService;


	@PostMapping(value = "/getRedisStringValue/{key}")
	public String getRedisStringValue(@PathVariable("key") String key) {
		return redisSampleService.getRedisStringValue(key);

	}

	@PostMapping(value = "/setRedisStringValue")
	public void setRedisStringValue(@RequestBody Map<String, Object> params) {
		redisSampleService.setRedisStringValue(params);
	}

	@PostMapping("/setSessionId")
	public void setSessionId(@RequestBody Map<String, Object> params, HttpSession session) {
		session.setAttribute("requestParam", params);
	}

	@PostMapping("/getSessionId")
	public Map<String, Object> getSessionId(HttpSession session, Model model) {
		Map<String, Object> result = new HashMap<>();
		log.info("session >>>>>>>>>" +session.getAttribute("requestParam"));
		result.put("test", session.getAttribute("requestParam"));

		return result;
	}

}
