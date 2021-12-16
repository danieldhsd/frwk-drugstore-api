package com.frwk.drugstore.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.frwk.drugstore.domain.model.enumeration.ProductStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductModel {

	private Long id;
	
	private String sku;
	
	private String name;
	
	private String description;
	
	private String photoPath;
	
	private BigDecimal price;
	
	private Long inventoryQuantity;
	
	private ProductStatus status;
	
	private CategoryModel category;
	
	private OffsetDateTime createdAt;
	
	private OffsetDateTime updatedAt;

}
