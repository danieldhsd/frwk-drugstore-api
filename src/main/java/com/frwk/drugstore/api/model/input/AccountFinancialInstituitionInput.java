package com.frwk.drugstore.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountFinancialInstituitionInput {

	@NotBlank
	private String account;
	
	@NotBlank
	private String agency;
	
	@NotNull
	private UserIdInput client;
	
	@NotNull
	private FinancialInstituitionIdInput financialInstituition;
}
