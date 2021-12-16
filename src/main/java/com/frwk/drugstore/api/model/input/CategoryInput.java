package com.frwk.drugstore.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryInput {

	@NotBlank
	private String name;
	
	private String description;

}
