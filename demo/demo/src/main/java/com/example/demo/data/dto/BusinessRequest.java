package com.example.demo.data.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.ToString;

@Getter
public class BusinessRequest {
	
	@Getter
	@ToString
	public static class Businesses{
		
		@Pattern(regexp = "^\\d{10}$", message = "10자리 숫자만 입력 가능합니다.")
		private String b_no;
		
		@NotBlank(message = "개업일자는 필수 값입니다.")
		private String start_dt;
		
		@NotBlank(message = "대표자성명은 필수 값입니다.")
		private String p_nm;
		private String p_nm2;
		private String b_nm;
		private String corp_no;
		private String b_sector;
		private String b_type;
		private String b_adr;
		
	}
	
	
}
