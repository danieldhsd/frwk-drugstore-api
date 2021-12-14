package com.frwk.drugstore.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.frwk.drugstore.api.model.OrderModel;
import com.frwk.drugstore.domain.model.Order;

@Component
public class OrderModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public OrderModel toModel(Order order) {
		return modelMapper.map(order, OrderModel.class);
	}
	
	public List<OrderModel> toCollectionModel(List<Order> orders) {
		return orders.stream()
				.map(order -> toModel(order))
				.collect(Collectors.toList());
	}
}
