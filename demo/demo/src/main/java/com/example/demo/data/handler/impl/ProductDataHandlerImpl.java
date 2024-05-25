package com.example.demo.data.handler.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.data.dao.ProductDAO;
import com.example.demo.data.entity.Product;
import com.example.demo.data.handler.ProductDataHandler;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductDataHandlerImpl implements ProductDataHandler{

	ProductDAO productDAO;
	
	@Autowired
	public ProductDataHandlerImpl(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}
	
	@Override
	public Product saveProductEntity(String productId, String productName, int productPrice, int productStock) {
		// TODO Auto-generated method stub
		Product productEntity = new Product(productId, productName, productPrice, productStock);
		
		return productDAO.saveProduct(productEntity);
	}
	
	@Override
	public Product getProductEntity(String productId) {
		// TODO Auto-generated method stub
		return productDAO.getProduct(productId);
	}
	
}
