package com.example.demo.data.dao;

import com.example.demo.common.exception.SunghunAPITestException;
import com.example.demo.data.entity.Product;

public interface ProductDAO {

	Product saveProduct(Product productEntity);
	
	Product getProduct(String productId);

	Product getProduct2(String productId) throws SunghunAPITestException;

	void deleteProduct(String productId);
}
