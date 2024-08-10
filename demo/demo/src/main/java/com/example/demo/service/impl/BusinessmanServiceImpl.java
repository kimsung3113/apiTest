package com.example.demo.service.impl;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.data.dto.BusinessRequest;
import com.example.demo.data.dto.BusinessResponse;
import com.example.demo.service.BusinessmanService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;

@Service
public class BusinessmanServiceImpl implements BusinessmanService{
		
	private final Logger LOGGER = LoggerFactory.getLogger(BusinessmanServiceImpl.class);
	
	private RestTemplate restTemplate;
	private ObjectMapper objectMapper;
	
	@PostConstruct
	public void init() {
		
		LOGGER.info("{} init 메서드 실행", BusinessmanServiceImpl.class.getName());
		
		this.restTemplate = getRestTemplate();
		this.objectMapper = new ObjectMapper();
		
	}
	
	
	// restTemplate 설정 메서드
	private RestTemplate getRestTemplate() {
		
		HttpClient httpClient = HttpClientBuilder.create()
				.setConnectionManager(PoolingHttpClientConnectionManagerBuilder.create()
						.setMaxConnTotal(100) // 최대로 연결할 수 있는 커넥션 쓰레드 수
						.setMaxConnPerRoute(60) // (IP + PORT) 당 커넥션 쓰레드 수
						.build()
						).build();

//				.evictIdleConnections(60L, TimeUnit.SECONDS) // 최대 연결 유효시간을 지정한다
//				.evictExpiredConnections() // 설정한 최대 연결 유효시간이 만료되면 커넥션을 해제한다
//				.setConnectionTimeToLive(5, TimeUnit.SECONDS) // 커넥션 만료시간을 설정한다

			HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
			factory.setConnectionRequestTimeout(5000 * 1000); //  HttpClient가 커넥션 풀에서 사용 가능한 연결을 가져오기 위해 대기하는 최대 시간을 지정하는 옵션입니다. 커넥션 풀에서 사용 가능한 연결이 없을 때, 요청은 커넥션 풀에 새로운 연결이 생성될 때까지 해당 시간만큼 대기합니다.
			factory.setConnectTimeout(5000); // 커넥션 최대 시간
			//factory.setReadTimeo(5000); // 읽기 최대 시간
			factory.setHttpClient(httpClient);

			return new RestTemplate(factory);
		
	}
	
	@Override
	public BusinessResponse.BusinessStatusResponseDto checkBusiRegistrationStatus(String portalKey, List<String> b_noList) {
		// TODO Auto-generated method stub
		
		URI uri = UriComponentsBuilder.fromUriString("https://api.odcloud.kr")
				.path("/api/nts-businessman/v1/status")
				.encode()
				.build()
				.toUri();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(new MediaType[] {MediaType.APPLICATION_JSON}));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Infuser " + portalKey);
		
		Map<String, List<String>> body = new HashMap<>();
		body.put("b_no", b_noList);
			
		HttpEntity<Map<String, List<String>>> entity = new HttpEntity<>(body, headers);
			
		ResponseEntity<BusinessResponse.BusinessStatusResponseDto> response = 
					restTemplate.exchange(uri, HttpMethod.POST, entity, BusinessResponse.BusinessStatusResponseDto.class);
			
		System.out.println("사업자등록 상태조회 API Response 값 : " + response.getBody().toString());
				
		return response.getBody();
	}
	
	// 사업자등록정보 진위확인 API
	@Override
	public BusinessResponse.BusinessValidateResponseDto verifyBusiRegistrationInfo(String portalKey, 
			List<BusinessRequest.Businesses> businesses) {
		// TODO Auto-generated method stub
		
		URI uri = UriComponentsBuilder.fromUriString("https://api.odcloud.kr")
				.path("api/nts-businessman/v1/validate")
				.encode()
				.build()
				.toUri();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(new MediaType[] {MediaType.APPLICATION_JSON}));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Infuser " + portalKey);
		
		Map<String, List<BusinessRequest.Businesses>> body = new HashMap<>();
		
//		List<Map<String, String>> businessMapList = new ArrayList<>();
		
//		if(businesses.size() >= 0) {
//			
//			for(int i = 0; i< businesses.size(); i++) {
//				
//				Map<String, String> map = objectMapper.convertValue(businesses.get(i), new TypeReference<Map<String, String>>(){});
//				businessMapList.add(map);
//			}
//		}
					
		body.put("businesses", businesses);
		System.out.println("map으로 변환 후 multivalueMap에 넣은 바디 : " + body);
		
		HttpEntity<Map<String, List<BusinessRequest.Businesses>>> entity = new HttpEntity<>(body, headers);
		
		ResponseEntity<BusinessResponse.BusinessValidateResponseDto> response 
					= restTemplate.exchange(uri, HttpMethod.POST, entity, BusinessResponse.BusinessValidateResponseDto.class);
		
		System.out.println("사업자등록정보 진위확인 API Response 값 : " + response.getBody().toString());
		
		return response.getBody();
	}

}
