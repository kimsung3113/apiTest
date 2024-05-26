package com.example.demo.common.valid;

import com.example.demo.common.annotation.ValidationAnnotation;

import lombok.Getter;

@Getter
public class TempDto {
	  
	@ValidationAnnotation 
	private String value;
	
}
