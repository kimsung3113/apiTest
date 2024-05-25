package com.example.demo.data.dto;

import com.example.demo.data.entity.Product;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProductDto {
	
	@NotNull
	//@Size(min = 8, max = 8)
	private String productId;
	
	@NotNull
	private String productName;
	
	@NotNull
	@Min(value = 500)
	@Max(value = 3000000)
	private int productPrice;
	
	@NotNull
	@Min(value = 0)
	@Max(value = 9999)
	private int productStock;
	
	public Product toEntity() {
		return Product.builder()
			.id(productId)
			.name(productName)
			.price(productPrice)
			.stock(productStock)
			.build();
	}
}
