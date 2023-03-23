package com.victory.aop;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableAspectJAutoProxy
@Aspect
@Component
public class LogAspect {


	@Around("execution(* com.victory..*Controller.*(..))")
	public Object LogBefore(ProceedingJoinPoint joinpoint)throws Throwable{

		Object proceed = joinpoint.proceed();


        Map<String, Object> resMap = new HashMap<String, Object>();

        if(proceed instanceof Map) {
        	ObjectMapper mapper = new ObjectMapper();	//
        	resMap.put("resultValue", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(proceed));
        }else {
        	resMap.put("resultValue", proceed);
        }

        return proceed;
	}
}
