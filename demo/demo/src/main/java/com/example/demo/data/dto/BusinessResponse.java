package com.example.demo.data.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@ToString
public class BusinessResponse{

	private Integer request_cnt;
	private String status_code;
	
	@Getter
	@Setter
	public static class BusinessStatusResponseDto extends BusinessResponse{

		private Integer match_cnt;
		
		private List<Data> data;				
		
		@Override
		public String toString() {
			return super.toString() + "BusinessStatusResponseDto [match_cnt=" + match_cnt + ", data=" + data + "]";
		}

		@Getter
		@Setter
		@ToString
		public static class Data{
			
			private String b_no;
			private String b_stt;
			private String b_stt_cd;
			private String tax_type;
			private String tax_type_cd;
			private String end_dt;
			private String utcc_yn;
			private String tax_type_change_dt;
			private String invoice_apply_dt;
			private String rbf_tax_type;
			private String rbf_tax_type_cd;
					
		}
				
	}
	
	@Getter
	public static class BusinessValidateResponseDto extends BusinessResponse{
		
		private Integer valid_cnt;
		
		private List<ValidateData> data;		
		
		
		@Override
		public String toString() {
			return super.toString() + "BusinessValidateResponseDto [valid_cnt=" + valid_cnt + ", data=" + data + "]";
		}

		@Getter
		public static class ValidateData{
			
			private String b_no;
			private String valid;
			private String valid_msg;
			
			private VerifyRequestParam request_param;
			
			private StatusResult status;			
			
			@Override
			public String toString() {
				return "ValidateData [b_no=" + b_no + ", valid=" + valid + ", valid_msg=" + valid_msg
						+ ", request_param=" + request_param + ", status=" + status + "]";
			}

			@Getter
			@ToString
			public static class VerifyRequestParam{
				
				private String b_no;
				private String start_dt;
				private String p_nm;
				private String p_nm2;
				private String b_nm;
				private String corp_no;
				private String b_sector;
				private String b_type;
				private String b_adr;
			}
			
			@Getter
			@ToString
			public static class StatusResult{
				
				private String b_no;
				private String b_stt;
				private String b_stt_cd;
				private String tax_type;
				private String tax_type_cd;
				private String end_dt;
				private String utcc_yn;
				private String tax_type_change_dt;
				private String invoice_apply_dt;
				private String rbf_tax_type;
				private String rbf_tax_type_cd;
			}
		}
	}
	
}



