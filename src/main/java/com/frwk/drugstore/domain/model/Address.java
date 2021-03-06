package com.frwk.drugstore.domain.model;

import javax.persistence.Embeddable;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Table(name = "tb_address")
@Getter
@Setter
public class Address {

	private String postalCode;
	
	private String street;
	
	private String number;
	
	private String neighborhood;
	
	private String city;
	
	private String state;
	
}
