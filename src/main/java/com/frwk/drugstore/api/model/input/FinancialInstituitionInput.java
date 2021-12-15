package com.frwk.drugstore.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FinancialInstituitionInput {

	@NotBlank
	private String name;
	
	@NotBlank
	private String code;
	
	private String description;
}
