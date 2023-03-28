package com.victory.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableAspectJAutoProxy
@Aspect
@Component
public class LogAspect {

	@Around("execution(* com.victory..*Controller.*(..)) && args(params,..)") //
	public Object logBefore(ProceedingJoinPoint joinpoint, Map<String, Object> params) throws Throwable {
		ObjectMapper mapper = new ObjectMapper();

		//HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();

		log.info("==== ReqParam : " + params);

		Object proceed = joinpoint.proceed();

		Map<String, Object> resMap = new HashMap<>();

		if (proceed instanceof Map) {
			//
			resMap.put("resultValue", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(proceed));
		} else {
			resMap.put("resultValue", proceed);
		}

		log.info("==== ResParam : " + resMap.get("resultValue"));
		return proceed;
	}
}
