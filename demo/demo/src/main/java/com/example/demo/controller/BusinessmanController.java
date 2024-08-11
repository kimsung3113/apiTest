package com.example.demo.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import com.example.demo.data.dto.BusinessRequest;
import com.example.demo.data.dto.BusinessResponse;
import com.example.demo.data.dto.BusinessResponse.BusinessValidateResponseDto.ValidateData.VerifyRequestParam;
import com.example.demo.service.BusinessmanService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/business")
public class BusinessmanController {

	private final Logger LOGGER = LoggerFactory.getLogger(BusinessmanController.class);
	
	@Autowired
	BusinessmanService businessmanService;
	
	@Value("${public.data.portal.key}")
	private String PORTAL_KEY;
	
	
	@GetMapping(value =  "/checkstatus")
	public BusinessResponse.BusinessStatusResponseDto getBusinessInquiryStatus(@RequestParam List<String> b_noList) {
		
		LOGGER.info("[getBusinessInquiryStatus] perform API. portalKey : {}", PORTAL_KEY);
		
		String pattern = "^\\d{10}$";
		
		boolean isValid = b_noList.stream().allMatch(str -> (str.matches(pattern)));
		
		if(!isValid) {
			throw new IllegalArgumentException("10자리의 숫자만 올 수 있습니다.");
		}
		
//		for(int i =0; i < b_noList.size(); i++) {
//			
//			// 무조건 10자리이고 숫자만 와야 합니다.
//			Matcher matcher = Pattern.compile(pattern).matcher(b_noList.get(i));
//			
//			if(!matcher.matches()) {
//				throw new IllegalArgumentException("10자리의 숫자만 올 수 있습니다.");
//			}
//		}
		
		
		return businessmanService.checkBusiRegistrationStatus(PORTAL_KEY, b_noList);
		
	}
	
	@PostMapping(value = "/verify/registration")
	public BusinessResponse.BusinessValidateResponseDto getBusinessRegistrationValidate
						(@RequestBody @Valid List<BusinessRequest.Businesses> businesses){
		
		LOGGER.info("[getBusinessRegistrationValidate] perform API. portalKey : {}", PORTAL_KEY);
		
		// b_no, start_dt, p_nm은 필수 파라미터 체크 해줘야된다. (없으면 에러 발생)
		System.out.println("requestbody 받은 데이터 : " + businesses.toString());
				
		
		return businessmanService.verifyBusiRegistrationInfo(PORTAL_KEY, businesses);
	}
	
	
}
