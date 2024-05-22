package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.data.dto.ProductDto;
import com.example.demo.data.entity.Product;
import com.example.demo.data.handler.ProductDataHandler;
import com.example.demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	ProductDataHandler productDataHandler;
	
	@Autowired
	public ProductServiceImpl(ProductDataHandler productDataHandler) {
		this.productDataHandler = productDataHandler;
	}
	
	@Override
	public ProductDto saveProduct(String productId, String productName, int productPrice, int productStock) {
		// TODO Auto-generated method stub
		Product productEntity = productDataHandler.saveProductEntity(productId, productName, productPrice, productStock);
		
		ProductDto productDto = new ProductDto(productEntity.getId(),
				productEntity.getName(), productEntity.getPrice(), productEntity.getStock());
		
		return productDto;
	}
	
	@Override
	public ProductDto getProduct(String productId) {
		// TODO Auto-generated method stub
		
		Product productEntity = productDataHandler.getProductEntity(productId);
		
		ProductDto ProductDto = new ProductDto(productEntity.getId(),
				productEntity.getName(), productEntity.getPrice(), productEntity.getStock());
		return ProductDto;
	}
	
}
