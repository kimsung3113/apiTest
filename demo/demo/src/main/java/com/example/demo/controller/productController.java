package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.Constants.ExceptionClass;
import com.example.demo.common.exception.SunghunAPITestException;
import com.example.demo.data.dto.ProductDto;
import com.example.demo.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/product-api")
public class productController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(productController.class);
	private ProductService productService;
	
	@Autowired
	public productController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping(value = "/product/{productId}")
	public ProductDto getProduct(@PathVariable String productId) {
		
		long startTime = System.currentTimeMillis();
		LOGGER.info("[ProductController] perform {} of Sunghun API", "getProduct");
		
		ProductDto productDto = productService.getProduct(productId);
		
		// 이런식으로 Log를 중괄호안에 값을 넣어 log 찍기.
		LOGGER.info("[ProductController] Response :: productId = {}, productName = {}, productPrice = {}, productStock = {}, Response Time = {}ms" ,
				productDto.getProductId(), productDto.getProductName(), productDto.getProductPrice(), productDto.getProductStock(), (System.currentTimeMillis() - startTime));
		
		
		return productDto;
	}
	
	// 상품 불러오기2 - optional로 상품 ID가 없을때 customException을 발생시키게 만든다.
	@GetMapping(value = "/product2")
	public ProductDto getProduct2(@RequestParam String productId) throws SunghunAPITestException{
		
		long startTime = System.currentTimeMillis();
		LOGGER.info("[ProductController] perform {} of Sunghun API", "getProduct2");
		
		ProductDto productDto = productService.getProduct2(productId);
		
		LOGGER.info("[ProductController] Response :: productId = {}, productName = {}, productPrice = {}, productStock = {}, Response Time = {}ms" ,
				productDto.getProductId(), productDto.getProductName(), productDto.getProductPrice(), productDto.getProductStock(), (System.currentTimeMillis() - startTime));
		
		return productDto;
		
	}
	
	
	@PostMapping(value = "/product")
	public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {
		
		// Validation Code Example - 이런 코드들은 계속 중복이 될 수 있기 때문에 @valid 어노테이션으로 대체 할 수 있는건 하는게 좋다.
		if(productDto.getProductId().equals("") || productDto.getProductId().isEmpty()) {
			LOGGER.error("[createProduct] failed Response :: productId is Empty");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(productDto);
		}
		
		String productId = productDto.getProductId();
		String productName = productDto.getProductName();
		int productPrice = productDto.getProductPrice();
		int productStock = productDto.getProductStock();
		
		ProductDto response = productService.saveProduct(productId, productName, productPrice, productStock);
		
		LOGGER.info("[createProduct] Response >> productId : {}, productName : {}, productPrice : {}, productStock : {}",
				response.getProductId(), response.getProductName(), response.getProductPrice(), response.getProductStock());
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
		
		
	}
	
	// 상품 삭제
	@DeleteMapping(value = "/product/{productId}/name/{productName}")
	public ResponseEntity<String> deleteProduct(@PathVariable String productId, @PathVariable String productName) throws SunghunAPITestException {
		
		ProductDto productDto = productService.getProduct(productId);
		
		System.out.println("가져온 product Id & productName : " + productDto.getProductId() + "Name :" + productDto.getProductName());
		System.out.println("매개변수 product Id & productName : " + productId + "Name :" + productName);
		
		
		if(productDto.getProductId().equals(productId) && productDto.getProductName().equals(productName)) {
			productService.deleteProduct(productId);
			return ResponseEntity.status(HttpStatus.OK).body("상품이 정상적으로 삭제가 되었습니다.");
			
		}else {
			throw new SunghunAPITestException(ExceptionClass.PRODUCT, HttpStatus.BAD_REQUEST, "삭제할 상품이 없습니다.");
		}
		
		
	}
	
	
	@PostMapping(value = "/product/exception")
	public void excpetionTest() throws SunghunAPITestException{
		throw new SunghunAPITestException(ExceptionClass.PRODUCT, HttpStatus.BAD_REQUEST, "의도한 에러가 발생했습니다.");
	}
	
	
}
