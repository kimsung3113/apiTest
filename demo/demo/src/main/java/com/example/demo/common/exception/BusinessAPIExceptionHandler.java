package com.example.demo.common.exception;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

// 왜 basePackages를 controller뒤에 controller명까지 붙였을때는 왜 안됬을까?
@RestControllerAdvice(basePackages = "com.example.demo.controller")
public class BusinessAPIExceptionHandler {
	
	private final Logger LOG = LoggerFactory.getLogger(BusinessAPIExceptionHandler.class);

	@ExceptionHandler(value =  HandlerMethodValidationException.class)
	public ResponseEntity<Map<String, Object>> handleValidationException(HandlerMethodValidationException ex){
		
		HttpHeaders header = new HttpHeaders();
		HttpStatus status =  HttpStatus.BAD_REQUEST;
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", status);
		
		Map<String, String> errors = new HashMap<>();
		
		ex.getAllErrors().forEach((error) ->{
			String field = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(field, errorMessage);
		});
		
// 	MethodArgumentNotValidException이 Wrapping이 되어 있는것이 아닌 그냥 HandlerMethodValidationException이었다.		
		
//		if(ex.getCause() instanceof MethodArgumentNotValidException) {
//			MethodArgumentNotValidException mex = (MethodArgumentNotValidException) ex.getCause();
//			
//			// 무슨 필드에 에러들이 났는지 찾는 용도. 
//			mex.getBindingResult().getAllErrors().forEach((error) -> {
//				String field = ((FieldError) error).getField();
//				String errorMessage = error.getDefaultMessage();
//				errors.put(field, errorMessage); 
//			});
//			
//		}
		
		if(errors.size() > 1) {
			result.put("reason", "Invalid Values");
		}else {
			result.put("reason", "Invalid Value");
		}
		
		LOG.info("Error 뭐가 들어있는지 체크 : " + errors);
		
		result.put("errors", errors);
		
		return new ResponseEntity<Map<String,Object>>(result, header, status);
	}
	
	@ExceptionHandler(value = IllegalArgumentException.class)
	public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex){
		
		Map<String, Object> result = new HashMap<>();
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		HttpHeaders headers = new HttpHeaders();
		
		LOG.info("Exception getMessage : " + ex.getMessage());
		LOG.info("Exception getCause : " + ex.getCause());
		LOG.info("Exception toString : " + ex.toString());
		
		result.put("code", status.getReasonPhrase());
		result.put("reason", "invalid value");
		result.put("message", ex.getMessage());
		
		return new ResponseEntity<Map<String,Object>>(result, headers, status);
	}
	
	
}
