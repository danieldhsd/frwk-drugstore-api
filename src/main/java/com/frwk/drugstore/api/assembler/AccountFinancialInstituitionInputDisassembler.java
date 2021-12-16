package com.frwk.drugstore.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.frwk.drugstore.api.model.input.AccountFinancialInstituitionInput;
import com.frwk.drugstore.domain.model.AccountFinancialInstituition;
import com.frwk.drugstore.domain.model.FinancialInstituition;
import com.frwk.drugstore.domain.model.User;

@Component
public class AccountFinancialInstituitionInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public AccountFinancialInstituition toDomainObject(AccountFinancialInstituitionInput accountInput) {
		return modelMapper.map(accountInput, AccountFinancialInstituition.class);
	}
	
	public void copyToDomainObject(AccountFinancialInstituitionInput accountInput, AccountFinancialInstituition account) {
		account.setClient(new User());
		account.setFinancialInstituition(new FinancialInstituition());
		
		modelMapper.map(accountInput, account);
	}
}
