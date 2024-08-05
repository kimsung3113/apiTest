package com.example.demo.service;

import org.springframework.http.ResponseEntity;

import com.example.demo.dto.Member;
import com.example.demo.dto.Member.Request.MemberBean;
import com.example.demo.dto.Member.Response;
import com.example.demo.dto.MemberDTO;

public interface RestTemplateService {

	public String getSunghunApiTest();
	
	public String getName();
	
	public String getName2();
	
	public ResponseEntity<MemberDTO> postDto();
	
	public ResponseEntity<MemberDTO> addHeader();

	public <T extends Member.Response> T changeMemberPlus(Member.Request.MemberBean memberBean, String resTypeName, Class<T> responseType);
	
}
