package com.example.demo.data.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.data.entity.Product;

@SpringBootTest
@ActiveProfiles("dev")
public class DevProductRepositoryTest {

  @Autowired ProductRepository productRepository;

  @Test
  void devTest() {
    Product product =
        Product.builder().id("testProduct").name("testDEV").price(1000).stock(500).build();

    productRepository.save(product);
  }
}