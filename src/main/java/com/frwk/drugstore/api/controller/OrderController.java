package com.frwk.drugstore.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.frwk.drugstore.api.assembler.OrderInputDisassembler;
import com.frwk.drugstore.api.assembler.OrderModelAssembler;
import com.frwk.drugstore.api.model.OrderModel;
import com.frwk.drugstore.api.model.input.OrderInput;
import com.frwk.drugstore.domain.model.Order;
import com.frwk.drugstore.domain.repository.OrderRepository;
import com.frwk.drugstore.domain.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderModelAssembler orderModelAssembler;
	
	@Autowired
	private OrderInputDisassembler orderInputDisassembler;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping
	public List<OrderModel> getAll() {
		return orderModelAssembler.toCollectionModel(orderRepository.findAll());
	}
	
	@GetMapping("/{idOrder}")
	public OrderModel findById(@PathVariable Long idOrder) {
		Order order = orderService.getOrThrowException(idOrder);
		
		return orderModelAssembler.toModel(order);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrderModel create(@RequestBody @Valid OrderInput orderInput) {
		Order order = orderInputDisassembler.toDomainObject(orderInput);
		order = orderService.create(order);
		
		return orderModelAssembler.toModel(order);
	}
}
