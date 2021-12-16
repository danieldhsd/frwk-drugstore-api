package com.frwk.drugstore.domain.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_order_item")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class OrderItem {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private BigDecimal unitPrice;
	
	private BigDecimal totalPrice;
	
	@NotNull
	private Integer quantity;
	
	private String observation;
	
	@ManyToOne
	@NotNull
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "orderimpl_id")
	private Order orderimpl;

}
