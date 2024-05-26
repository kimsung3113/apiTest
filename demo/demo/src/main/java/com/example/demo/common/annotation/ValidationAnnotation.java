package com.example.demo.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.example.demo.common.valid.ParameterValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;


// 보통 단방향으로 흐르게끔 설계하는데 이 어노테이션은 양쪽에서 서로 물고 있다.
// 더이상 확장 될일이 없어 그렇다!!
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
// 이 어노테이션이 어떤 Validate를 따르는지 설정
@Constraint(validatedBy = ParameterValidator.class)
public @interface ValidationAnnotation {
	
  
  String message() default "Invalid Value. It should be 'hello'";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
