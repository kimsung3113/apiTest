package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;


// 이것이 거의 전부라 redisTemplate은 이런식으로 사용하면 된다.
@Component
public class RedisTemplateTestImpl {

	private final RedisTemplate<String, String> redisTemplate;
	
	@Autowired
	RedisTemplateTestImpl(RedisTemplate<String, String> redisTemplate){
		this.redisTemplate = redisTemplate;
	}
	
	public void test() {
		
		ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
		valueOperations.set("redisTemplateTest", "test");
		
	}
	
}
