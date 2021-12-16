package com.frwk.drugstore.api.model;

import java.time.OffsetDateTime;
import java.util.List;

import com.frwk.drugstore.domain.model.Address;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DrugstoreModel {

	private Long id;
	
	private String name;
	
	private String cnpj;

	private String email;
	
	private List<String> phones;
	
	private Address address;
	
	private OffsetDateTime createdAt;
	
	private OffsetDateTime updatedAt;

}
