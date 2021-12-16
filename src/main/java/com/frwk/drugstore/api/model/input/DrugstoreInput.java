package com.frwk.drugstore.api.model.input;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CNPJ;

import com.frwk.drugstore.domain.model.Address;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DrugstoreInput {

	@NotBlank
	private String name;
	
	@CNPJ
	@NotBlank
	private String cnpj;

	@Email
	private String email;
	
	private List<String> phones;
	
	private Address address;

}
