package com.example.demo.service;

import java.util.List;

import com.example.demo.data.dto.BusinessRequest;
import com.example.demo.data.dto.BusinessResponse;


public interface BusinessmanService {

	BusinessResponse.BusinessStatusResponseDto checkBusiRegistrationStatus(String portalKey, List<String> b_noList);

	BusinessResponse.BusinessValidateResponseDto verifyBusiRegistrationInfo(String portalKey, List<BusinessRequest.Businesses> businesses);

}
