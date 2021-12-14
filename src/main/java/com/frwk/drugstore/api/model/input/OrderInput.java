package com.frwk.drugstore.api.model.input;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.frwk.drugstore.domain.model.enumeration.PaymentMethod;

public class OrderInput {

	private DrugstoreIdInput drugstore;
	
	@NotEmpty
	private List<OrderItemInput> orderItems;
	
	private UserIdInput client;
	
	@CPF
	private String cpfClient;
	
	private PaymentMethod paymentMethod;

	public DrugstoreIdInput getDrugstore() {
		return drugstore;
	}

	public void setDrugstore(DrugstoreIdInput drugstore) {
		this.drugstore = drugstore;
	}

	public List<OrderItemInput> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemInput> orderItems) {
		this.orderItems = orderItems;
	}

	public UserIdInput getClient() {
		return client;
	}

	public void setClient(UserIdInput client) {
		this.client = client;
	}

	public String getCpfClient() {
		return cpfClient;
	}

	public void setCpfClient(String cpfClient) {
		this.cpfClient = cpfClient;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
}
