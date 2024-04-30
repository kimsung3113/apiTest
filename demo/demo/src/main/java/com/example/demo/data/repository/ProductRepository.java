package com.example.demo.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.data.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, String>{

}
