package com.frwk.drugstore.api.model;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FinancialInstituitionModel {

	private Long id;
	
	private String name;
	
	private String code;
	
	private String description;
	
	private OffsetDateTime createdAt;
	
	private OffsetDateTime updatedAt;
}
