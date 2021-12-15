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

import com.frwk.drugstore.api.assembler.FinancialInstituitionInputDisassembler;
import com.frwk.drugstore.api.assembler.FinancialInstituitionModelAssembler;
import com.frwk.drugstore.api.model.FinancialInstituitionModel;
import com.frwk.drugstore.api.model.input.FinancialInstituitionInput;
import com.frwk.drugstore.domain.model.FinancialInstituition;
import com.frwk.drugstore.domain.repository.FinancialInstituitionRepository;
import com.frwk.drugstore.domain.service.FinancialInstituitionService;

@RestController
@RequestMapping("/financial-instituitions")
public class FinancialInstituitionController {

	@Autowired
	private FinancialInstituitionService instituitionService;
	
	@Autowired
	private FinancialInstituitionRepository instituitionRepository;
	
	@Autowired
	private FinancialInstituitionModelAssembler instituitionModelAssembler;
	
	@Autowired
	private FinancialInstituitionInputDisassembler instituitionInputDisassembler;
	
	@GetMapping
	public List<FinancialInstituitionModel> getAll() {
		return instituitionModelAssembler.toCollectionModel(instituitionRepository.findAll());
	}
	
	@GetMapping("/{idInstituition}")
	public FinancialInstituitionModel findById(@PathVariable Long idInstituition) {
		FinancialInstituition instituition = instituitionService.getOrThrowException(idInstituition);
		
		return instituitionModelAssembler.toModel(instituition);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FinancialInstituitionModel create(@RequestBody @Valid FinancialInstituitionInput instituitionInput) {
		FinancialInstituition instituition = instituitionInputDisassembler.toDomainObject(instituitionInput);
		instituition = instituitionService.create(instituition);
		
		return instituitionModelAssembler.toModel(instituition);
	}
	
	@PutMapping("/{idInstituition}")
	public FinancialInstituitionModel update(@PathVariable Long idInstituition, 
						@RequestBody @Valid FinancialInstituitionInput instituitionInput) {
		FinancialInstituition instituition = instituitionService.getOrThrowException(idInstituition);
		instituitionInputDisassembler.copyToDomainObject(instituitionInput, instituition);
		
		return instituitionModelAssembler.toModel(instituitionService.create(instituition));
	}
	
	@DeleteMapping("/{idInstituition}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long idInstituition) {
		instituitionService.remove(idInstituition);
	}
}
