package com.frwk.drugstore.api.model.input;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.frwk.drugstore.domain.model.enumeration.PaymentMethod;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderInput {

	private DrugstoreIdInput drugstore;
	
	@NotEmpty
	private List<OrderItemInput> orderItems;
	
	private UserIdInput client;
	
	@CPF
	private String cpfClient;
	
	private PaymentMethod paymentMethod;

}
