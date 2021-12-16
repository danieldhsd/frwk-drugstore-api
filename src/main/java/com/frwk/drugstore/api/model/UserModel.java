package com.frwk.drugstore.api.model;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.frwk.drugstore.domain.model.Address;
import com.frwk.drugstore.domain.model.enumeration.UserType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModel {

	private Long id;
	
	private String name;
	
	private String cpf;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate birthday;
	
	private String email;
	
	private UserType userType;
	
	private Address address;
	
	private OffsetDateTime createdAt;
	
	private OffsetDateTime updatedAt;

}
