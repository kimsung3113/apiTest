package com.example.demo.util;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.example.demo.dto.Member;

// 사용자에게 파라미터로 받아 responsetype을 확정한다.
public class memberResClassMapper {

	private static final Map<String, Class<? extends Member.Response>> responseTypeMap = new HashMap<>();
	private static Set<String> responseTypeNameSet = new HashSet<String>();
	
	static {
		
		responseTypeMap.put("MemberPlus", Member.Response.MemberPlusBean.class);
		responseTypeMap.put("MemberVip", Member.Response.MemberVipBean.class);
		
		 // Using reflection to find all subclasses of Member.Response
//        for (Class<?> innerClass : Member.Response.class.getDeclaredClasses()) {
//            // Ensure the class is a subclass of Member.Response and is not abstract(추상 클래스는 뺀다)
//            if (Member.Response.class.isAssignableFrom(innerClass) && !Modifier.isAbstract(innerClass.getModifiers())) {
//            	 try {
//                     @SuppressWarnings("unchecked")
//                     Class<? extends Member.Response> responseClass = (Class<? extends Member.Response>) innerClass;
//                     responseTypeMap.put(innerClass.getSimpleName(), responseClass);
//                 } catch (ClassCastException e) {
//                     // Log or handle the exception as necessary
//                     System.err.println("Failed to cast class: " + innerClass.getName());
//                 }
//            }
//        }
		
		responseTypeNameSet = responseTypeMap.keySet();
		
		System.out.println("ClassMapper static box 끝.");
			
	}
	
	public static Class<? extends Member.Response> getResponseType(String responseType) {
		return responseTypeMap.get(responseType);
	}
	
	public static Set<String> getResponseTypeNameSet() {
		return responseTypeNameSet;
	}
	
}
