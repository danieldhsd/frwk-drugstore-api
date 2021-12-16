package com.frwk.drugstore.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.frwk.drugstore.api.model.input.FinancialInstituitionInput;
import com.frwk.drugstore.domain.model.FinancialInstituition;

@Component
public class FinancialInstituitionInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public FinancialInstituition toDomainObject(FinancialInstituitionInput financialInstituitionInput) {
		return modelMapper.map(financialInstituitionInput, FinancialInstituition.class);
	}
	
	public void copyToDomainObject(FinancialInstituitionInput financialInstituitionInput, FinancialInstituition financialInstituition) {
		modelMapper.map(financialInstituitionInput, financialInstituition);
	}
}
