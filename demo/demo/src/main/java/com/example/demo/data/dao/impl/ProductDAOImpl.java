package com.example.demo.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.data.dao.ProductDAO;
import com.example.demo.data.entity.Product;
import com.example.demo.data.repository.ProductRepository;

@Service
public class ProductDAOImpl implements ProductDAO{

	@Autowired
	ProductRepository productRepository;
	
//	@Autowired
//	public ProductDAOImpl(ProductRepository productRepository) {
//		this.productRepository = productRepository;
//	}
	
	@Override
	public Product saveProduct(Product productEntity) {
		// TODO Auto-generated method stub
		productRepository.save(productEntity);	// DB에 저장
		return productEntity;
	}
	
	@Override
	public Product getProduct(String productId) {
		// TODO Auto-generated method stub
		Product productEntity = productRepository.getById(productId);	//id로 entity 데이터로 가져옴.
		return productEntity;
	}
}
