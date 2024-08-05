package com.example.demo.service.impl;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.dto.Member;
import com.example.demo.dto.Member.Request.MemberBean;
import com.example.demo.dto.Member.Response.MemberPlusBean;
import com.example.demo.dto.MemberDTO;
import com.example.demo.service.RestTemplateService;

@Service
public class RestTemplateServiceImpl implements RestTemplateService{

	private final Logger LOGGER = LoggerFactory.getLogger(RestTemplateServiceImpl.class);
	
	@Override
	public String getSunghunApiTest() {
		
		URI uri = UriComponentsBuilder
			.fromUriString("http://localhost:9091")
			.path("/api/server/sunghun")
			.encode()
			.build()
			.toUri();
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
		
		LOGGER.info("status code : {}", responseEntity.getStatusCode());
		LOGGER.info("body : {}", responseEntity.getBody());
		
		return responseEntity.getBody();
	}
	
	@Override
	public String getName() {
		
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:9091")
				.path("/api/server/name")
				.queryParam("name", "Sunghun")
				.encode()
				.build()
				.toUri();
			
			RestTemplate restTemplate = new RestTemplate();
			
			ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
			
			LOGGER.info("status code : {}", responseEntity.getStatusCode());
			LOGGER.info("body : {}", responseEntity.getBody());
			
			return responseEntity.getBody();
	}
	
	@Override
	public String getName2() {
		
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:9091")
				.path("/api/server/path-variable/{name}")
				.encode()
				.build()
				.expand("Sunghun2") // 복수의 값을 넣어야 할 경우 , 를 추가해여 구분한다.
				.toUri();
			
			RestTemplate restTemplate = new RestTemplate();
			
			ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
			
			LOGGER.info("status code : {}", responseEntity.getStatusCode());
			LOGGER.info("body : {}", responseEntity.getBody());
			
			return responseEntity.getBody();
	}
	
	
	@Override
	public ResponseEntity<MemberDTO> postDto() {
		
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:9091")
				.path("/api/server/member")
				.queryParam("name", "Member Sunghun")
				.queryParam("age", "29")
				.queryParam("weight", "65")
				.encode()
				.build()
				.toUri();
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setName("Sunghun!!");
		memberDTO.setAge("31");
		memberDTO.setWeight("70");
		
			
		RestTemplate restTemplate = new RestTemplate();
			
		ResponseEntity<MemberDTO> responseEntity = restTemplate.postForEntity(uri, memberDTO, MemberDTO.class);
			
		LOGGER.info("status code : {}", responseEntity.getStatusCode());
		LOGGER.info("body : {}", responseEntity.getBody());
		
		return responseEntity;
	}
	
	@Override
	public ResponseEntity<MemberDTO> addHeader() {
		
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:9091")
				.path("/api/server/add-header")
				.encode()
				.build()
				.toUri();
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setName("Sunghun!!");
		memberDTO.setAge("31");
		memberDTO.setWeight("70");
		
		RequestEntity<MemberDTO> requestEntity = RequestEntity
				.post(uri)
				.header("around-header", "Sunghun header")
				.body(memberDTO);
			
		RestTemplate restTemplate = new RestTemplate();
			
		ResponseEntity<MemberDTO> responseEntity = restTemplate.exchange(requestEntity, MemberDTO.class);
			
		LOGGER.info("status code : {}", responseEntity.getStatusCode());
		LOGGER.info("body : {}", responseEntity.getBody());
		
		return responseEntity;
	}
	
	@Override
	public <T extends Member.Response> T changeMemberPlus(Member.Request.MemberBean memberBean, String resTypeName, Class<T> responseType) {
		// TODO Auto-generated method stub
		
		URI uri = UriComponentsBuilder
					.fromUriString("http://localhost:9091")
					.path("/api/server/change-MemberPlus")
					.encode()
					.build()
					.toUri();
		
//		Member.Request.MemberBean memberBean = new Member.Request.MemberBean();
//		memberBean.setName("Kim Sung Hun");
//		memberBean.setAge("30");
//		memberBean.setWeight("64");
		
		String headerName, headerValue;
		
		if(resTypeName.equals("MemberPlus")) {
			headerName = "XXX-MemberPlus-Header";
			headerValue = "Member-Plus Header";
		}else{
			headerName = "XXX-MemberVip-Header";
			headerValue = "Member-Vip Header";
		}
		
		// HeaderName과 Value도 동적으로
		RequestEntity<Member.Request.MemberBean> requestEntity = RequestEntity
				.post(uri)
				.header(headerName, headerValue)
				.body(memberBean);
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<T> responseEntity = restTemplate.exchange(requestEntity, responseType);
		
		LOGGER.info("status code : {}", responseEntity.getStatusCode());
		LOGGER.info("body : {}", responseEntity.getBody());
		
		return responseEntity.getBody();
	}
	
}
