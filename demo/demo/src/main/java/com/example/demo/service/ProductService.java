package com.example.demo.service;

import com.example.demo.common.exception.SunghunAPITestException;
import com.example.demo.data.dto.ProductDto;

public interface ProductService {

	ProductDto saveProduct(String productId, String productName, int productPrice, int productStock);
	
	ProductDto getProduct(String productId);

	ProductDto getProduct2(String productId) throws SunghunAPITestException;

	void deleteProduct(String productId);
	
}
