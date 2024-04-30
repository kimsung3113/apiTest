package com.example.demo.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.MemberDTO;

@RestController
@RequestMapping(value = "/api/v1/put-api")
public class PutController {

	
	// http://localhost:9091/api/v1/put-api/member body값에 json 형식으로 넣는다.
		@PutMapping(value = "/member")
		public String postMember(@RequestBody Map<String, Object> postData) {
			StringBuilder sb = new StringBuilder();
			
			postData.entrySet().forEach(map ->{
				sb.append(map.getKey() + " : " + map.getValue() + "\n");
			});
			
			return sb.toString();
		}
		
		
		// DTO의 toString형태로 MemberDTO [name=김성훈, age=29, weight=29] responseBody가 나온다.
		@PutMapping(value = "/member2")
		public String postMemberDto1(@RequestBody MemberDTO memberDto) {
			return memberDto.toString();
		}
		
		// 보통 이식으로 클라이언트에게 보내는것을 선호한다.
		/*	Json의 형태로 responseBody가 나온다.
		 * // { "name": "김성훈", "age": "29", "weight": "65" }
		 */
		@PutMapping(value = "/member3")
		public MemberDTO postMemberDto2(@RequestBody MemberDTO memberDto) {
			return memberDto;
		}
		
		// Status는 아래 Enum 클래스로 맞춰서 나온다.!!!!!!!
		/*	위처럼 Json의 형태로 responseBody가 나온다.
		 * // { "name": "김성훈", "age": "29", "weight": "65" }
		 */
		@PutMapping(value = "/member4")
		public ResponseEntity<MemberDTO> postMemberDto3(@RequestBody MemberDTO memberDto) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(memberDto);
		}
		
		
}
