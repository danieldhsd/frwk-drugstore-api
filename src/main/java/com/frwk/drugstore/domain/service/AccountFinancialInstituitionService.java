package com.frwk.drugstore.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.frwk.drugstore.domain.exception.EntityInUseException;
import com.frwk.drugstore.domain.exception.EntityNotFoundException;
import com.frwk.drugstore.domain.model.AccountFinancialInstituition;
import com.frwk.drugstore.domain.model.FinancialInstituition;
import com.frwk.drugstore.domain.model.User;
import com.frwk.drugstore.domain.repository.AccountFinancialInstituitionRepository;

@Service
public class AccountFinancialInstituitionService {

	@Autowired
	private AccountFinancialInstituitionRepository accountRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FinancialInstituitionService financialInstituitionService;
	
	public AccountFinancialInstituition create(AccountFinancialInstituition account) {
		User user = userService.getOrThrowException(account.getClient().getId());
		FinancialInstituition financialInstituition = financialInstituitionService.getOrThrowException(account.getFinancialInstituition().getId());
		
		account.setClient(user);
		account.setFinancialInstituition(financialInstituition);
		
		return accountRepository.save(account);
	}
	
	public void remove(Long idAccount) {
		try {
			accountRepository.deleteById(idAccount);
		
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException("Account not found");
			
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException("Account can't be removed.");
		}
	}
	
	public AccountFinancialInstituition getOrThrowException(Long idAccount) {
		return accountRepository.findById(idAccount).orElseThrow(() -> new EntityNotFoundException("Account not found!"));
	}
}
