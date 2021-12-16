package com.frwk.drugstore.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.frwk.drugstore.api.model.AccountFinancialInstituitionModel;
import com.frwk.drugstore.domain.model.AccountFinancialInstituition;

@Component
public class AccountFinancialInstituitionModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public AccountFinancialInstituitionModel toModel(AccountFinancialInstituition account) {
		return modelMapper.map(account, AccountFinancialInstituitionModel.class);
	}
	
	public List<AccountFinancialInstituitionModel> toCollectionModel(List<AccountFinancialInstituition> accounts) {
		return accounts.stream()
				.map(account -> toModel(account))
				.collect(Collectors.toList());
	}
}
