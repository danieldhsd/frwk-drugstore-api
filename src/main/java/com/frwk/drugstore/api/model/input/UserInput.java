package com.frwk.drugstore.api.model.input;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.frwk.drugstore.domain.model.Address;
import com.frwk.drugstore.domain.model.enumeration.UserType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInput {

	@NotBlank
	private String name;
	
	@CPF
	@NotBlank
	private String cpf;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate birthday;
	
	@Email
	@NotBlank
	private String email;
	
	@NotNull
	private UserType userType;
	
	private Address address;

}
