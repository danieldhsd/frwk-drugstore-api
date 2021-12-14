package com.frwk.drugstore.api.model.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class OrderItemInput {

	private BigDecimal unitPrice;
	
	private Integer quantity;
	
	private String observation;
	
	@NotNull
	private ProductIdInput product;

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public ProductIdInput getProduct() {
		return product;
	}

	public void setProduct(ProductIdInput product) {
		this.product = product;
	}
}
