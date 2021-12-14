package com.frwk.drugstore.api.model.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.frwk.drugstore.domain.model.enumeration.ProductStatus;

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

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Long getInventoryQuantity() {
		return inventoryQuantity;
	}

	public void setInventoryQuantity(Long inventoryQuantity) {
		this.inventoryQuantity = inventoryQuantity;
	}

	public ProductStatus getStatus() {
		return status;
	}

	public void setStatus(ProductStatus status) {
		this.status = status;
	}

	public CategoryIdInput getCategory() {
		return category;
	}

	public void setCategory(CategoryIdInput category) {
		this.category = category;
	}

}
