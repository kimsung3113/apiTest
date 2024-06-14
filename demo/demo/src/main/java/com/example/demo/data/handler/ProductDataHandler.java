package com.example.demo.data.handler;

import com.example.demo.common.exception.SunghunAPITestException;
import com.example.demo.data.entity.Product;

public interface ProductDataHandler {

	Product saveProductEntity(String productId, String productName, int productPrice, int productStock);
	
	Product getProductEntity(String productId);

	Product getProductEntityOptional(String productId) throws SunghunAPITestException;
	
	
}
