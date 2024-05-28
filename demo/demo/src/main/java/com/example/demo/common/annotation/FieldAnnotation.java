package com.example.demo.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 반복되는 작업들을 줄일라고 사용하는 경우가 많다.Runtime으로 설정한 것들이 태반이다.
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface FieldAnnotation {
	  String name();

	  String value();
	}
	

