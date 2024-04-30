package com.example.demo.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.MemberDTO;

@RestController
@RequestMapping(value = "/api/v1/post-api")
public class PostController {

	// http://localhost:9091/api/v1/post-api/member body값에 json 형식으로 넣는다.
	@PostMapping(value = "/member")
	public String postMember(@RequestBody Map<String, Object> postData) {
		StringBuilder sb = new StringBuilder();
		
		postData.entrySet().forEach(map ->{
			sb.append(map.getKey() + " : " + map.getValue() + "\n");
		});
		
		return sb.toString();
	}
	
	// http://localhost:9091/api/v1/post-api/member2 body값에 json 형식으로 넣는다.
	@PostMapping(value = "/member2")
	public String postMemberDto(@RequestBody MemberDTO memberDto) {
		return memberDto.toString();
	}
}
