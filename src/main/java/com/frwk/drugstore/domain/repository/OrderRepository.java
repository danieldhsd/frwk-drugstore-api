package com.frwk.drugstore.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frwk.drugstore.domain.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	
}
