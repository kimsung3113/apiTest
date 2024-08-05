package com.example.demo.data.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.common.Constants.ExceptionClass;
import com.example.demo.common.exception.SunghunAPITestException;
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
		
		//Product productEntity = productRepository.getById(productId);	//id로 entity 데이터로 가져옴.
		
		// 위 메서드는 안에서 return 으로 getReferenceById를 호출만 한다. SimpleJpaRepository 클래스에서 deprecated되어 바꿨다. 
		Product productEntity = productRepository.getReferenceById(productId);	//id로 entity 데이터로 가져옴.
		
		// 값 체크
		Product testProduct = productRepository.findByIdIs(productId);
		List<Product> testProduct1 =productRepository.findByIdIsNot(productId);
		Optional<Product> testProduct2 =productRepository.findById(productId);
		List<Product> testProduct3 =productRepository.findByIdNot(productId);
		
		
		System.out.println("findByIdIs메서드 : " + testProduct);
		System.out.println("findByIdIsNot메서드 : " + testProduct1);
		System.out.println("findById메서드 : " + testProduct2);
		System.out.println("findByIdNot메서드 : " + testProduct3);
		
		return productEntity;
	}
	
	@Override
	public Product getProduct2(String productId) throws SunghunAPITestException{
		// TODO Auto-generated method stub
		
//		Product productEntity = productRepository.findById(productId)
//				.orElseThrow(() -> new SunghunAPITestException(ExceptionClass.PRODUCT, HttpStatus.NO_CONTENT, "상품 ID가 없습니다."));
		
		return productRepository.findById(productId)
				.orElseThrow(() -> new SunghunAPITestException(ExceptionClass.PRODUCT, HttpStatus.NOT_FOUND, "Input ProductId does not exists in Data"));
	}
	
	@Override
	public void deleteProduct(String productId) {
		// TODO Auto-generated method stub
		
		productRepository.deleteById(productId);
		
	}
	
}
