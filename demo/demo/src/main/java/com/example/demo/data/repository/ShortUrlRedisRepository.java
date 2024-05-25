package com.example.demo.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.data.dto.ShortUrlResponseDto;

public interface ShortUrlRedisRepository extends CrudRepository<ShortUrlResponseDto, String>{

}
