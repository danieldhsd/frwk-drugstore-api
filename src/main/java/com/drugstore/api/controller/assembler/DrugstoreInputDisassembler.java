package com.drugstore.api.controller.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.drugstore.api.controller.model.input.DrugstoreInput;
import com.drugstore.api.domain.model.Drugstore;

@Component
public class DrugstoreInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Drugstore toDomainObject(DrugstoreInput drugstoreInput) {
		return modelMapper.map(drugstoreInput, Drugstore.class);
	}
}
