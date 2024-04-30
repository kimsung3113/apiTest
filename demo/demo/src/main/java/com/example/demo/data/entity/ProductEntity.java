package com.example.demo.data.entity;

import com.example.demo.data.dto.ProductDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product")	// entity기반으로 테이블 자동 생성
public class ProductEntity {

	@Id
	String productId;
	
	String productName;
	
	Integer productPrice;
	
	Integer productStock;
	
	public ProductDto toDto() {
		return ProductDto.builder()
			.productId(productId)
			.productName(productName)
			.productPrice(productPrice)
			.productStock(productStock)
			.build();
		
	}
}
