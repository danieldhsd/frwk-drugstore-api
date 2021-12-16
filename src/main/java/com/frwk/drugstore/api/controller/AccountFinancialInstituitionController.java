package com.frwk.drugstore.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.frwk.drugstore.api.assembler.AccountFinancialInstituitionInputDisassembler;
import com.frwk.drugstore.api.assembler.AccountFinancialInstituitionModelAssembler;
import com.frwk.drugstore.api.model.AccountFinancialInstituitionModel;
import com.frwk.drugstore.api.model.input.AccountFinancialInstituitionInput;
import com.frwk.drugstore.domain.model.AccountFinancialInstituition;
import com.frwk.drugstore.domain.repository.AccountFinancialInstituitionRepository;
import com.frwk.drugstore.domain.service.AccountFinancialInstituitionService;

@RestController
@RequestMapping("/accounts")
public class AccountFinancialInstituitionController {

	@Autowired
	private AccountFinancialInstituitionRepository accountRepository;
	
	@Autowired
	private AccountFinancialInstituitionService accountService;
	
	@Autowired
	private AccountFinancialInstituitionInputDisassembler accountInputDisassembler;
	
	@Autowired
	private AccountFinancialInstituitionModelAssembler accountModelAssembler;
	
	@GetMapping
	public List<AccountFinancialInstituitionModel> getAll() {
		return accountModelAssembler.toCollectionModel(accountRepository.findAll());
	}
	
	@GetMapping("/{idAccount}")
	public AccountFinancialInstituitionModel findById(@PathVariable Long idAccount) {
		AccountFinancialInstituition account = accountService.getOrThrowException(idAccount);
		
		return accountModelAssembler.toModel(account);
	}
	
	@GetMapping("/users/{idUser}")
	public List<AccountFinancialInstituitionModel> getAllByIdUser(@PathVariable Long idUser) {
		return accountModelAssembler.toCollectionModel(accountRepository.findAllByIdUser(idUser));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AccountFinancialInstituitionModel create(@RequestBody @Valid AccountFinancialInstituitionInput accountInput) {
		AccountFinancialInstituition account = accountInputDisassembler.toDomainObject(accountInput);
		account = accountService.create(account);

		return accountModelAssembler.toModel(account);
	}
	
	@PutMapping("/{idAccount}")
	public AccountFinancialInstituitionModel update(@PathVariable Long idAccount, 
							@RequestBody @Valid AccountFinancialInstituitionInput accountInput) throws Exception {
		AccountFinancialInstituition account = accountService.getOrThrowException(idAccount);
		accountInputDisassembler.copyToDomainObject(accountInput, account);
		
		return accountModelAssembler.toModel(accountService.create(account));
	}
	
	@DeleteMapping("/{idAccount}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long idAccount) {
		accountService.remove(idAccount);
	}
}
