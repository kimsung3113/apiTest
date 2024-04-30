package com.example.demo.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.MemberDTO;

@RestController
@RequestMapping(value = "/api/v1/get-api")
public class GetController {

	// http://localhost:8080/api/v1/get-api/variable1/firstVariableTest
	@GetMapping(value = "/variable1/{variable}")
	public String variable1(@PathVariable String variable) {		
		return variable;
	}
	
	// http://localhost:8080/api/v1/get-api/variable2/secondVariableTest
	@GetMapping(value = "/variable2/{variable}")
	public String variable2(@PathVariable("variable") String variable) {		
		return variable;
	}
	
	//http://localhost:8080/api/v1/get-api/request2?name=김성훈&age=29&weight=65
	@GetMapping(value = "/request2")
	public String requestParam1(@RequestParam Map<String, String> params) {
		
		StringBuilder sb = new StringBuilder();
		
		params.entrySet().forEach(map ->
			sb.append(map.getKey() + " : " + map.getValue() + "\n")
		);
				
		return sb.toString();
	}
	//http://localhost:8080/api/v1/get-api/request3?name=김성훈&age=29&weight=65
	@GetMapping(value = "/request3")
	public String requestParam2(MemberDTO memberDto) {
		return memberDto.toString();
	}
	
}
