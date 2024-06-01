package com.example.demo.common.exception;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SunghunAPITestExceptionHandler {

	private final Logger LOGGER = LoggerFactory.getLogger(SunghunAPITestExceptionHandler.class);
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
	public ResponseEntity<Map<String, String>> ExceptionHandler(Exception e){
		HttpHeaders responseHeaders = new HttpHeaders();
		
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		
		LOGGER.info(e.getLocalizedMessage());
		LOGGER.info("Advice내 ExceptionHandler 호출");
		
		Map<String, String> map = new HashMap<>();
		map.put("error type", httpStatus.getReasonPhrase());
		map.put("code", "400");
		map.put("message", "에러 발생");
		
		return new ResponseEntity<>(map, responseHeaders, httpStatus);
	}
	
	// 
	@org.springframework.web.bind.annotation.ExceptionHandler(value = SunghunAPITestException.class)
	public ResponseEntity<Map<String, String>> ExceptionHandler(SunghunAPITestException e){
		HttpHeaders responseHeaders = new HttpHeaders();
		
		LOGGER.info(e.getLocalizedMessage());
		LOGGER.info("Advice내 SunghunAPITestExceptionHandler 호출");
		
		Map<String, String> map = new HashMap<>();
		map.put("error type", e.getHttpStatusType());
		map.put("error code", Integer.toString(e.getHttpStatusCode()));	// Map<String, Object>로 설정하면 변환작업은 불필요하다.
		map.put("message", e.getMessage());
		
		return new ResponseEntity<>(map, responseHeaders, e.getHttpStatus());
	}
}
