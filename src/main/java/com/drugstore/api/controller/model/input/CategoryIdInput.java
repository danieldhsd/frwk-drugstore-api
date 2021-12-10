package com.drugstore.api.controller.model.input;

import javax.validation.constraints.NotNull;

public class CategoryIdInput {

	@NotNull
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
