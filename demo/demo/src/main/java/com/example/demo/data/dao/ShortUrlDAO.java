package com.example.demo.data.dao;

import com.example.demo.data.entity.ShortUrl;

public interface ShortUrlDAO {

	  ShortUrl saveShortUrl(ShortUrl shortUrl);

	  ShortUrl getShortUrl(String originalUrl);

	  ShortUrl getOriginalUrl(String shortUrl);

	  ShortUrl updateShortUrl(ShortUrl newShortUrl);

	  void deleteByShortUrl(String shortUrl);

	  void deleteByOriginalUrl(String originalUrl);
	}
