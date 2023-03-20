package com.victory.biz.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.victory.biz.model.ResultVo;
import com.victory.biz.model.TestVo;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class TestController {

	@PostMapping(value = "/PostTest")
	public ResultVo PostTest(@RequestBody TestVo param) throws Exception {

		log.debug("param : " +  param);

		return ResultVo.builder().result("PostTest").resultMsg("PostTest 성공").build();

	}

	@GetMapping(value = "/GetTest")
	public ResultVo GetTest(@RequestParam TestVo param) throws Exception {

		log.debug("param : " +  param);


		return ResultVo.builder().result("GetTest").resultMsg("GetTest 성공").build();

	}

}
