package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
	
	public DemoApplicationTests() {
		 //TODO Auto-generated constructor stub
		System.setProperty("jasypt.encrypt.password", "sunghun_api_test");
		System.setProperty("spring.profiles.active", "local");
	}

	@Test
	void contextLoads() {
		System.out.println("DemoApplicationTests contextLoads 실행");
	}

}
