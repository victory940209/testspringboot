package com.victory.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.victory.model.ResultVo;
import com.victory.model.TestVo;
import com.victory.system.HttpUrlConnectUtil;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "test", description = "test")
@Slf4j
@RestController
public class TestController {


	@Autowired
	HttpUrlConnectUtil apiCon;

	@PostMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> test(@RequestBody Map<String, Object> param) throws Exception {

		log.debug("param : {}", param);

		return Map.of("key1", "value1", "key2", "value2");

	}

	@PostMapping(value = "/testVo")
	public TestVo testVo(@RequestBody TestVo param) throws Exception {

		log.debug("param : {}", param);

		TestVo tv = TestVo.builder().test("returnVal").test2("returnVal2").test3("returnVal3").test4(1).build();

		log.debug("return vo : {}", tv);

		return tv;

	}

	@PostMapping(value = "/testResultVoPost")
	public ResultVo testResultVoPost(@RequestBody Map<String, Object> param) throws Exception {

		log.debug("param : {}", param);

		ResultVo tv = ResultVo.builder().result("resultasd0").resultMsg("resultMsggasdasd").build();

		log.debug("return vo : {}", tv);

		return tv;

	}

	@GetMapping(value = "/testResultVoGet")
	public ResultVo testResultVoGet(@RequestParam Map<String, Object> param) throws Exception {

		log.debug("param : {}", param);

		ResultVo tv = ResultVo.builder().result("resultasd0").resultMsg("resultMsggasdasd").build();

		log.debug("return vo : {}", tv);

		return tv;

	}

	@PostMapping(value = "/apiConPost")
	public TestVo apiConPost(@RequestBody Map<String, Object> param, @RequestHeader Map<String, Object> requestHeader)
			throws Exception {

		String url = param.get("url").toString();
		String port = param.get("port").toString();
		String resulturl = "http://127.0.0.1:" + port + "/" + url;

		TestVo forObject = new TestVo();
		try {

			forObject = apiCon.apiPost(resulturl, param, requestHeader, TestVo.class);
			log.info("###end-point`s return value : {}", forObject);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return forObject;
	}

	@PostMapping(value = "/apiConPostVo")
	public ResultVo apiConPostVo(@RequestBody TestVo param, @RequestHeader Map<String, Object> requestHeader)
			throws Exception {

		String resulturl = "http://127.0.0.1:" + "20030" + "/" + "testVo";

		ResultVo forObject = new ResultVo();
		try {

			forObject = apiCon.apiPost(resulturl, param, requestHeader, ResultVo.class);
			log.info("###end-point`s return value : {}", forObject);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return forObject;
	}

	@PostMapping(value = "/apiConGet")
	public TestVo apiConGet(@RequestBody Map<String, Object> param, @RequestHeader Map<String, Object> requestHeader)
			throws Exception {

		String url = param.get("url").toString();
		String port = param.get("port").toString();
		String resulturl = "http://127.0.0.1:" + port + "/" + url;

		TestVo forObject = new TestVo();
		try {

			forObject = apiCon.apiGet(resulturl, param, requestHeader, TestVo.class);
			log.info("###end-point`s return value : {}", forObject);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return forObject;
	}

	@PostMapping(value = "/apiConGetVo")
	public TestVo apiConGetVo(@RequestBody TestVo param, @RequestHeader Map<String, Object> requestHeader)
			throws Exception {

		String resulturl = "http://127.0.0.1:" + param.getTest() + "/" + param.getTest2();

		TestVo forObject = new TestVo();
		try {

			forObject = apiCon.apiGet(resulturl, param, requestHeader, TestVo.class);
			log.info("###end-point`s return value : {}", forObject);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return forObject;
	}
}
