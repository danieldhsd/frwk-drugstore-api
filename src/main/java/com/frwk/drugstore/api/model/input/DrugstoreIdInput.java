package com.frwk.drugstore.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DrugstoreIdInput {

	@NotNull
	private Long id;

}
