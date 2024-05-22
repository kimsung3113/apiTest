package com.example.demo.data.dto;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
// redis에 저장을 할때는 직렬화 역직렬화를 진행 할 수도 있는데 
// 어떻게 설정하느냐에 따라서 key값에 prefix로 붙을 수 있는 value값을 설정을 해주는것.
// serializable도 구현해놓는게 좋다.(EH cache같은 것을 쓸때 필요하기 때문
@RedisHash(value = "shortUrl", timeToLive = 60)
public class ShortUrlResponseDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8092380441015611229L;

	@Id
	private String orgUrl;

	private String shortUrl;
}
