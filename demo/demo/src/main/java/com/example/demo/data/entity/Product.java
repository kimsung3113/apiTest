package com.example.demo.data.entity;

import com.example.demo.data.dto.ProductDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "product")	// entity기반으로 테이블 자동 생성
public class Product extends BaseEntity{

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	String id;
	
	String name;
	
	Integer price;
	
	Integer stock;
	
	public ProductDto toDto() {
		return ProductDto.builder()
			.productId(id)
			.productName(name)
			.productPrice(price)
			.productStock(stock)
			.build();
		
	}
}
