package com.frwk.drugstore.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import com.frwk.drugstore.domain.model.enumeration.PaymentMethod;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

	
}
