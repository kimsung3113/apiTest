package com.example.demo.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.data.entity.ShortUrl;

//쿼리메서드를 구현을 하고 구현체를 만들지 않아도 자동으로 jpaRepository쪽에서 알아서 메서드 생성을 해준다.
public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {

	  ShortUrl findByUrl(String url);

	  ShortUrl findByOrgUrl(String originalUrl);
	}
