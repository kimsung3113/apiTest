package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

	@GetMapping(value = "/hello")
	public String hello() {
		return "Hello Sunghun API";
	}
	
	@PostMapping(value = "log-test")
	public void logTest() {
		
		LOGGER.trace("Trace Log");
		LOGGER.debug("Debug Log");
		LOGGER.info("Info Log");
		LOGGER.warn("Warn Log");
		LOGGER.error("Error Log");
		
	}
	
	@PostMapping("/exception")
	public void exceptionTest() throws Exception{
		throw new Exception();
	}
	
//	@org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
//	public ResponseEntity<Map<String, String>> ExceptionHandler(Exception e){
//		HttpHeaders responseHeaders = new HttpHeaders();
//		
//		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
//		
//		LOGGER.info(e.getMessage());
//		LOGGER.info("Controller내 ExceptionHandler 호출");
//		
//		Map<String, String> map = new HashMap<>();
//		map.put("error type", httpStatus.getReasonPhrase());
//		map.put("code", "400");
//		map.put("message", "에러 발생");
//		
//		return new ResponseEntity<>(map, responseHeaders, httpStatus);
//	}
	
}
