package com.frwk.drugstore.api.model.input;

import javax.validation.constraints.NotBlank;

public class UserInputWithPassword extends UserInput {

	@NotBlank
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
