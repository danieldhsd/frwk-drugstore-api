package com.frwk.drugstore.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.frwk.drugstore.api.model.input.DrugstoreInput;
import com.frwk.drugstore.domain.model.Drugstore;

@Component
public class DrugstoreInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Drugstore toDomainObject(DrugstoreInput drugstoreInput) {
		return modelMapper.map(drugstoreInput, Drugstore.class);
	}
}
