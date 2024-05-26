package com.example.demo.common.valid;

import com.example.demo.common.annotation.ValidationAnnotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

// 내가 만든 어노테이션에 적용을 할거야, 어떤 값들을 체크를 할 건지
public class ParameterValidator implements ConstraintValidator<ValidationAnnotation, String> {
	  
	// 초기화 할 작업들이 있을때 사용 -> 그런 값이 없으면 override 안해도 된다.
	@Override
	  public void initialize(ValidationAnnotation constraintAnnotation) {
	    ConstraintValidator.super.initialize(constraintAnnotation);
	  }

	  @Override
	  public boolean isValid(String value, ConstraintValidatorContext context) {
	    return value != null && value.equals("hello");
	  }
	}