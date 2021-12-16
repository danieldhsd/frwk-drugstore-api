package com.frwk.drugstore.api.model.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.frwk.drugstore.domain.model.enumeration.ProductStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductInput {

	@NotBlank
	private String sku;
	
	@NotBlank
	private String name;
	
	private String description;
	
	private String photoPath;
	
	@NotNull
	@PositiveOrZero
	private BigDecimal price;
	
	@PositiveOrZero
	@NotNull
	private Long inventoryQuantity;
	
	private ProductStatus status;
	
	@NotNull
	private CategoryIdInput category;

}
