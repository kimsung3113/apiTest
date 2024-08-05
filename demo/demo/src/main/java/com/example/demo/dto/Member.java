package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public abstract class Member {
	
	private static final String myStructure = "Restemplate Structure";
	
	
	public abstract static class Request{
		
		@Getter
		@Setter
		@ToString
		public static class MemberBean extends Request{
			
			private String name;
			private String age;
			private String weight;
			
		}
		
		@Getter
		@Setter
		@ToString
		public static class MemberPlusReq extends Request{
			
			private String name;
			private String age;
			private String weight;
			private String memberPlusReq;
			
		}
	}
	
	public abstract static class Response{
		
		@Getter
		@Setter
		@ToString
		public static class MemberPlusBean extends Response{
			
			private String name;
			private String age;
			private String weight;
			private String memberPlus;		
			
		}
		
		@Getter
		@Setter
		@ToString
		public static class MemberVipBean extends Response{
			
			
			private String name;
			private String age;
			private String weight;
			private String memberVip;	
		}
		
		
		
	}
	
	

}
