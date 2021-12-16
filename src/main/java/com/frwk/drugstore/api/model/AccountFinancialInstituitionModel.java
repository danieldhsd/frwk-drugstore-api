package com.frwk.drugstore.api.model;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountFinancialInstituitionModel {

	private Long id;
	
	private String account;
	
	private String agency;
	
	private UserModel client;
	
	private FinancialInstituitionModel financialInstituition;
	
	private OffsetDateTime createdAt;
	
	private OffsetDateTime updatedAt;
}
