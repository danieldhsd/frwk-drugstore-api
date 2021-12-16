package com.frwk.drugstore.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.br.CPF;

import com.frwk.drugstore.domain.model.enumeration.PaymentMethod;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_order")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class Order {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Drugstore drugstore;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
			orphanRemoval = true, targetEntity = OrderItem.class,
			mappedBy = "orderimpl")
	@Fetch(FetchMode.SUBSELECT)
	@NotEmpty
	private List<OrderItem> orderItems;
	
	@ManyToOne
	private User client;
	
	@CPF
	private String cpfClient;
	
	@Column(nullable = false)
	private BigDecimal totalValue;
	
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;
	
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "timestamp")
	private OffsetDateTime createdAt;
	
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "timestamp")
	private OffsetDateTime updatedAt;
	
	@PreUpdate
	public void preUpdate() {
		updatedAt = OffsetDateTime.now();
	}
	
}
