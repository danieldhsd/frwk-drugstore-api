package com.frwk.drugstore.api.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemModel {

	private Long id;
	
	private BigDecimal unitPrice;
	
	private BigDecimal totalPrice;
	
	private Integer quantity;
	
	private String observation;
	
	private ProductModel product;
	
}
