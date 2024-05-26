package com.example.demo.controller;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.annotation.FieldAnnotation;
import com.example.demo.common.annotation.MethodAnnotation;
import com.example.demo.common.annotation.TypeAnnotation;
import com.example.demo.common.valid.TempDto;

import jakarta.validation.Valid;

@RestController
@TypeAnnotation(name = "Hello?", value = "World")
public class HelloController {
	
  @FieldAnnotation(name = "returnValue", value = "Bye World!")
  public String returnValue = "Hello World!";
	
	private final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

	@GetMapping(value = "/hello")
	public String hello() {
		return "Hello Sunghun API";
	}
	
	@RequestMapping("/hello1")
	@MethodAnnotation(name = "Hello1", value = "World1")
	public String hello1() throws NoSuchMethodException {
	    Method method = this.getClass().getMethod("hello1");
	    
	    // 어노테이션 객체 배열을 나열해준다.
	    Annotation[] annotations = method.getDeclaredAnnotations();

	    // 어노테이션이 Runtime 어노테이션인지 체크
	    for (Annotation annotation : annotations) {
	    	
	    // 이 어노테이션이 MethodAnnotation인지 체크
	      if (annotation instanceof MethodAnnotation) {
	        MethodAnnotation methodAnnotation = (MethodAnnotation) annotation;
	        return methodAnnotation.name() + ", " + methodAnnotation.value();
	      }
	    }
	    return "Hello World!";
	  }
	
	 @RequestMapping("/hello2")
	  public String hello2(@RequestBody @Valid TempDto dto) {
	    return "Valid value : " + dto.getValue();
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
