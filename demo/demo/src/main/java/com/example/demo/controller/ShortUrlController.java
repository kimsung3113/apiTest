package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.data.dto.ShortUrlResponseDto;
import com.example.demo.service.ShortUrlService;

@RestController
@RequestMapping("/api/v1/short-url")
public class ShortUrlController {

	private final Logger LOGGER = LoggerFactory.getLogger(ShortUrlController.class);
	
	@Value("${sunghun.api.short.uri.id}")
	private String CLIENT_ID; 
	
	@Value("${sunghun.api.short.uri.secret}")
	private String CLIENT_SECRET; 
	
	ShortUrlService shortUrlService;
	
	@Autowired
	  public ShortUrlController(ShortUrlService shortUrlService) {
	    this.shortUrlService = shortUrlService;
	  }

	  @PostMapping()
	  public ShortUrlResponseDto generateShortUrl(String originalUrl) {
	    LOGGER.info(
	        "[generateShortUrl] perform API. CLIENT_ID : {}, CLIENT_SECRET : {}",
	        CLIENT_ID,
	        CLIENT_SECRET);

	    return shortUrlService.generateShortUrl(CLIENT_ID, CLIENT_SECRET, originalUrl);
	  }

	  @GetMapping()
	  public ShortUrlResponseDto getShortUrl(String originalUrl) {
	    long startTime = System.currentTimeMillis();
	    
	    ShortUrlResponseDto shortUrlResponseDto =
	        shortUrlService.getShortUrl(CLIENT_ID, CLIENT_SECRET, originalUrl);
	    
	    long endTime = System.currentTimeMillis();

	    LOGGER.info("[getShortUrl] response Time : {}ms", (endTime - startTime));

	    return shortUrlResponseDto;
	  }

	  @PutMapping("/")
	  public ShortUrlResponseDto updateShortUrl(String originalUrl) {
	    return null;
	  }

		/*
		 * TODO 에러 잡기
		 * 
		 * org.springframework.dao.InvalidDataAccessApiUsageException: Entity must not
		 * be null at
		 * com.example.demo.data.dao.impl.ShortUrlDaoImpl.deleteByOriginalUrl(
		 * ShortUrlDaoImpl.java:58) at
		 * com.example.demo.service.impl.ShortUrlServiceImpl.deleteByOriginalUrl(
		 * ShortUrlServiceImpl.java:145) at
		 * com.example.demo.service.impl.ShortUrlServiceImpl.deleteShortUrl(
		 * ShortUrlServiceImpl.java:134) at
		 * com.example.demo.controller.ShortUrlController.deleteShortUrl(
		 * ShortUrlController.java:70)
		 */
	  @DeleteMapping("/")
	  public ResponseEntity<String> deleteShortUrl(String url) {
	    try {
	      shortUrlService.deleteShortUrl(url);
	    } catch (RuntimeException e) {
	      e.printStackTrace();
	    }

	    return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
	  }
	
}
