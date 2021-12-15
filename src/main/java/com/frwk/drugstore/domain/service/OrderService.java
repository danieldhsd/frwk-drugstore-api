package com.frwk.drugstore.domain.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frwk.drugstore.domain.exception.EntityNotFoundException;
import com.frwk.drugstore.domain.exception.GenericException;
import com.frwk.drugstore.domain.model.Drugstore;
import com.frwk.drugstore.domain.model.Order;
import com.frwk.drugstore.domain.model.OrderItem;
import com.frwk.drugstore.domain.model.Product;
import com.frwk.drugstore.domain.model.User;
import com.frwk.drugstore.domain.repository.OrderRepository;
import com.frwk.drugstore.domain.repository.ProductRepository;
import com.frwk.drugstore.domain.repository.UserRepository;

@Service
public class OrderService {

	@Autowired
	private DrugstoreService drugstoreService;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductRepository productRepository;
	
	private Map<Long, Long> productsQuantityInInventory;
	
	@Transactional
	public Order create(Order order) {
		setProductFromItems(order.getOrderItems());
		checkQuantityProductsInInventory(order.getOrderItems());
		calculateTotalPricePerItem(order.getOrderItems());
		calculateTotalOrderValue(order);
		
		setOrderDrugstore(order);
		setOrderClient(order);
		
		order = orderRepository.save(order);
		
		updateInventoryQuantityFromProducts();
		
		return order;
	}
	
	public Order getOrThrowException(Long idOrder) {
		return orderRepository.findById(idOrder).orElseThrow(() -> new EntityNotFoundException("Order not found!"));
	}
	
	private void setProductFromItems(List<OrderItem> orderItems) {
		orderItems.forEach(item -> {
			Product product = productService.getOrThrowException(item.getProduct().getId());
			item.setProduct(product);
		});
	}
	
	private void checkQuantityProductsInInventory(List<OrderItem> orderItems) {
		productsQuantityInInventory = new HashMap<>();
		
		orderItems.forEach(item -> {
			if(!productsQuantityInInventory.containsKey(item.getProduct().getId())) {
				productsQuantityInInventory.put(item.getProduct().getId(), item.getProduct().getInventoryQuantity());
			}
		});
		
		orderItems.forEach(item -> {
			Long newInventoryQuantity = productsQuantityInInventory.get(item.getProduct().getId()) - item.getQuantity();
			
			if(newInventoryQuantity < 0) {
				throw new GenericException("Don't have enough of product " + item.getProduct().getName() + " in inventory.");
			}
			
			productsQuantityInInventory.put(item.getProduct().getId(), newInventoryQuantity);
		});
	}
	
	private void calculateTotalPricePerItem(List<OrderItem> orderItems) {
		if(orderItems == null || orderItems.isEmpty()) return;
		
		orderItems.forEach(item -> {
			BigDecimal unitPrice = item.getUnitPrice() != null 
							? item.getUnitPrice() : item.getProduct().getPrice();
			item.setTotalPrice(unitPrice.multiply(BigDecimal.valueOf(item.getQuantity())));
		});
	}
	
	private void calculateTotalOrderValue(Order order) {
		Function<OrderItem, BigDecimal> totalMapper = OrderItem -> OrderItem.getTotalPrice();
		BigDecimal totalValue = order.getOrderItems()
					.stream()
					.map(totalMapper)
					.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		order.setTotalValue(totalValue);
	}
	
	private void setOrderDrugstore(Order order) {
		Drugstore drugstore = drugstoreService.getOrThrowException(order.getDrugstore().getId());
		order.setDrugstore(drugstore);
	}
	
	private void setOrderClient(Order order) {
		if(order.getCpfClient() != null 
				&& (order.getClient() == null || order.getClient().getId() == null)) {
			Optional<User> user = userRepository.findFirstByCpf(order.getCpfClient());
			order.setClient(user.get());
		}
	}
	
	private void updateInventoryQuantityFromProducts() {
		for(Map.Entry<Long, Long> productMap : productsQuantityInInventory.entrySet()) {
			Product product = productService.getOrThrowException(productMap.getKey());
			
			product.setInventoryQuantity(productMap.getValue());
			productRepository.save(product);
		}
	}
	
}
