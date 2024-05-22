package com.example.demo.data.dao;

import com.example.demo.data.entity.Product;

public interface ProductDAO {

	Product saveProduct(Product productEntity);
	
	Product getProduct(String productId);
}
