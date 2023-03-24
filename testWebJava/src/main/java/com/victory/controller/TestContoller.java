package com.victory.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class TestContoller {

	@GetMapping(value = "/test.do")
	public String test(@RequestParam Map<String, Object> param) throws Exception {
		log.info("asdfasdfasdf");
		return "index";

	}
}
