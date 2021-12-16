package com.frwk.drugstore.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInputWithPassword extends UserInput {

	@NotBlank
	private String password;

}
