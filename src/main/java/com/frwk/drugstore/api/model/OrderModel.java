package com.frwk.drugstore.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import com.frwk.drugstore.domain.model.enumeration.PaymentMethod;

public class OrderModel {

	private Long id;
	
	private DrugstoreModel drugstore;
	
	private List<OrderItemModel> orderItems;
	
	private UserModel client;
	
	private String cpfClient;
	
	private BigDecimal totalValue;
	
	private PaymentMethod paymentMethod;
	
	private OffsetDateTime createdAt;
	
	private OffsetDateTime updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DrugstoreModel getDrugstore() {
		return drugstore;
	}

	public void setDrugstore(DrugstoreModel drugstore) {
		this.drugstore = drugstore;
	}

	public List<OrderItemModel> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemModel> orderItems) {
		this.orderItems = orderItems;
	}

	public UserModel getClient() {
		return client;
	}

	public void setClient(UserModel client) {
		this.client = client;
	}

	public String getCpfClient() {
		return cpfClient;
	}

	public void setCpfClient(String cpfClient) {
		this.cpfClient = cpfClient;
	}

	public BigDecimal getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(BigDecimal totalValue) {
		this.totalValue = totalValue;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(OffsetDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public OffsetDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(OffsetDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}
