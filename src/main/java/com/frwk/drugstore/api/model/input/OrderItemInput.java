package com.frwk.drugstore.api.model.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemInput {

	private BigDecimal unitPrice;
	
	@NotNull
	private Integer quantity;
	
	private String observation;
	
	@NotNull
	private ProductIdInput product;

}
