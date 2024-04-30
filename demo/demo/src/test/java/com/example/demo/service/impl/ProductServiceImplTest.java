package com.example.demo.service.impl;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.data.dto.ProductDto;
import com.example.demo.data.entity.ProductEntity;
import com.example.demo.data.handler.impl.ProductDataHandlerImpl;

// 2가지 방법.
// 내가 어떤 객체를 가지고 올지 모르겠다 할시 - @SpringBootTest 어노테이션으로
// => 이유는 매개변수가 없을때만(뒤의 classes가 없을때) 전체 Bean을 로드 하기 때문.

// 2. 필요한것만 갔다가 쓰는 방법 @ExtendWith, @import로 아래처럼 사용.
// => @ExtendWith는 @SpringBootTest에 포함되어 있다.
// 아래 에러가 뜬다..
// => Could not detect default configuration classes for test class [com.example.demo.service.impl.ProductServiceImplTest]: ProductServiceImplTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.

@SpringBootTest(classes = {ProductDataHandlerImpl.class, ProductServiceImpl.class})
//@ExtendWith(SpringExtension.class)
@Import({ProductDataHandlerImpl.class, ProductServiceImpl.class})
public class ProductServiceImplTest {

	// ProductServiceImpl에서도 아래 productDataHandler를 주입하기 때문에 MockMvc 객체(Mock Bean)로 만들어준다.
	@MockBean
	ProductDataHandlerImpl productDataHandler;
	
	// Service 관련 통신 할 거고 Controller가 관련된 Test가 아니기 때문에 WebMvcTest가 아니다.
	// 그래서 Test 하고자 하는 객체를 주입을 받아줘야 한다.
	@Autowired
	ProductServiceImpl productService;
	
	@Test
	public void getProductTest() {
		
		//given - 가정사항 세팅.
		Mockito.when(productDataHandler.getProductEntity("123"))	// 메서드 후출시
		 	.thenReturn(new ProductEntity("123", "pen", 2000, 1000)); // 이것을 리턴
		
		ProductDto productDto = productService.getProduct("123");
		
		Assertions.assertEquals(productDto.getProductId(), "123");
		Assertions.assertEquals(productDto.getProductName(), "pen");
		Assertions.assertEquals(productDto.getProductPrice(), 2000);
		Assertions.assertEquals(productDto.getProductStock(), 1000);
		
		BDDMockito.verify(productDataHandler).getProductEntity("123");
		
	}
	
	@Test
	public void saveProductTest() {
		
		//given
		Mockito.when(productDataHandler.saveProductEntity("123", "pen", 2000, 1000))	
		 	.thenReturn(new ProductEntity("123", "pen", 2000, 1000));
		
		ProductDto productDto = productService.saveProduct("123", "pen", 2000, 1000);
		
		Assertions.assertEquals(productDto.getProductId(), "123");
		Assertions.assertEquals(productDto.getProductName(), "pen");
		Assertions.assertEquals(productDto.getProductPrice(), 2000);
		Assertions.assertEquals(productDto.getProductStock(), 1000);
		
		BDDMockito.verify(productDataHandler).saveProductEntity("123", "pen", 2000, 1000);
		
	}
	
	
}
