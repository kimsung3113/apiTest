package com.example.demo.common.exception;

import org.springframework.http.HttpStatus;

import com.example.demo.common.Constants;

public class SunghunAPITestException extends Exception{

	/**
	 * 
	 */
	// 직렬화를 위해 serialVersionUid 생성
	private static final long serialVersionUID = 2688186474788395234L;
	
	
	private Constants.ExceptionClass exceptionClass;
	private HttpStatus httpStatus;
	
	public SunghunAPITestException(Constants.ExceptionClass exceptionClass, HttpStatus httpStatus, String message) {
		super(exceptionClass.toString() + message);
		this.exceptionClass = exceptionClass;
		this.httpStatus = httpStatus;
	}
	
	public Constants.ExceptionClass getExceptionClass(){
		return exceptionClass;
	}
	
	
	public int getHttpStatusCode() {
		return httpStatus.value();
	}
	
	
	public String getHttpStatusType() {
		return httpStatus.getReasonPhrase();
	}
	
	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	
	
	
}
