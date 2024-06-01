package com.example.demo.data.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.data.entity.Product;

@SpringBootTest
@ActiveProfiles("local")
public class LocalProductRepositoryTest {

  @Autowired ProductRepository productRepository;

  
  // -Dspring.profiles.active=local로 설정해 test 완료
  @Test
  void devTest() {
    Product product =
        Product.builder().id("Localtest").name("testlocal").price(1000).stock(500).build();

    productRepository.save(product);
  }
}
