package com.example.demo.config.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisCacheConfig implements CacheConfig {

	  @Value("${spring.data.redis.host}")
	  private String host;

	  @Value("${spring.data.redis.port}")
	  private int port;

	  @Bean
	  public RedisConnectionFactory redisConnectionFactory() {
		//필수다. 기본적으로 사용하고 있는 redis의 클라이언트 디펜던시를 사용(host,port만 넘겨서 사용)
	    return new LettuceConnectionFactory(host, port);
	  }

	  @Bean
	  public RedisTemplate<?, ?> redisTemplate() {
	    RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();
	    redisTemplate.setConnectionFactory(redisConnectionFactory());
	    return redisTemplate;
	  }
	
}
