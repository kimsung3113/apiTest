package com.example.demo.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Member;
import com.example.demo.dto.Member.Response.MemberPlusBean;
import com.example.demo.dto.MemberDTO;
import com.example.demo.service.RestTemplateService;
import com.example.demo.util.memberResClassMapper;

@RestController
@RequestMapping("/api/rest-template")
public class RestTemplateController {

	RestTemplateService restTemplateService;
	
	@Autowired
	public RestTemplateController(RestTemplateService restTemplateService) {
		this.restTemplateService = restTemplateService;
	}
	
	@GetMapping(value = "/sunghun")
	public String getSunghunApiTest() {
		return restTemplateService.getSunghunApiTest();
	}
	
	@GetMapping(value = "/name")
	public String getName() {
		return restTemplateService.getName();
	}
	
	@GetMapping(value = "/name2")
	public String getName2() {
		return restTemplateService.getName2();
	}
	
	@PostMapping(value = "/dto")
	public ResponseEntity<MemberDTO> postDto(){
		return restTemplateService.postDto();
	}
	
	@PostMapping(value = "/add-header")
	public ResponseEntity<MemberDTO> addHeader(){
		return restTemplateService.addHeader();
	}
	
	@PostMapping(value = "/change-MemberPlus")
	public Member.Response changeStructure(@RequestBody Member.Request.MemberBean memberBean, @RequestParam String typeName){
		
		// typeName이 모두 담겨있는 set 가져온다.
		Set<String> typeNameSet = memberResClassMapper.getResponseTypeNameSet();
		
		if(typeName == null || typeName.isEmpty() || !typeNameSet.contains(typeName)) {
			throw new IllegalArgumentException("Invalid type Name : " + typeName);
		}
		
		Class<? extends Member.Response> responseType = memberResClassMapper.getResponseType(typeName);
		
		return restTemplateService.changeMemberPlus(memberBean, typeName, responseType);				
	}
	
	
}
