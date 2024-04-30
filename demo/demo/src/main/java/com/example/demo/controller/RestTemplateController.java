package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.MemberDTO;
import com.example.demo.service.RestTemplateService;

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
	
	
}
