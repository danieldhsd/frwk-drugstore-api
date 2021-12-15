package com.frwk.drugstore.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.frwk.drugstore.api.model.FinancialInstituitionModel;
import com.frwk.drugstore.domain.model.FinancialInstituition;

@Component
public class FinancialInstituitionModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public FinancialInstituitionModel toModel(FinancialInstituition financialInstituition) {
		return modelMapper.map(financialInstituition, FinancialInstituitionModel.class);
	}
	
	public List<FinancialInstituitionModel> toCollectionModel(List<FinancialInstituition> financialInstituitions) {
		return financialInstituitions.stream()
				.map(financialInstituition -> toModel(financialInstituition))
				.collect(Collectors.toList());
	}
}
