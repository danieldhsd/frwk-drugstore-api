package com.drugstore.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.drugstore.api.controller.assembler.DrugstoreInputDisassembler;
import com.drugstore.api.controller.assembler.DrugstoreModelAssembler;
import com.drugstore.api.controller.model.DrugstoreModel;
import com.drugstore.api.controller.model.input.DrugstoreInput;
import com.drugstore.api.domain.model.Drugstore;
import com.drugstore.api.domain.repository.DrugstoreRepository;
import com.drugstore.api.domain.service.DrugstoreService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/drugstores")
public class DrugstoreController {

	@Autowired
	private DrugstoreRepository drugstoreRepository;
	
	@Autowired
	private DrugstoreService drugstoreService;

	@Autowired
	private DrugstoreModelAssembler drugstoreModelAssembler;
	
	@Autowired
	private DrugstoreInputDisassembler drugstoreInputDisassembler;
	
	@GetMapping
	public List<DrugstoreModel> getAll() {
		return drugstoreModelAssembler.toCollectionModel(drugstoreRepository.findAll());
	}
	
	@GetMapping("/{idDrugstore}")
	public DrugstoreModel findById(@PathVariable Long idDrugstore) {
		Drugstore drugstore = drugstoreService.getOrThrowException(idDrugstore);
		
		return drugstoreModelAssembler.toModel(drugstore);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DrugstoreModel create(@RequestBody @Valid DrugstoreInput drugstoreInput) {
		Drugstore drugstore = drugstoreInputDisassembler.toDomainObject(drugstoreInput);
		drugstore = drugstoreService.create(drugstore);
		
		return drugstoreModelAssembler.toModel(drugstore);
	}
	
	@PutMapping("/{idDrugstore}")
	public DrugstoreModel update(@PathVariable Long idDrugstore, @RequestBody Map<String, Object> fields) {
		Drugstore drugstore = drugstoreService.getOrThrowException(idDrugstore);
		
		merge(fields, drugstore);
		
		return drugstoreModelAssembler.toModel(drugstoreService.create(drugstore));
	}
	
	@DeleteMapping("/{idDrugstore}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long idDrugstore) {
		drugstoreService.remove(idDrugstore);
	}
	
	private void merge(Map<String, Object> fields, Drugstore storedDrugstore) {
		ObjectMapper objectMapper = new ObjectMapper();
		Drugstore originDrugstore = objectMapper.convertValue(fields, Drugstore.class);
		
		fields.forEach((key, value) -> {
			Field field = ReflectionUtils.findField(Drugstore.class, key);
			field.setAccessible(true);
			
			Object newValue = ReflectionUtils.getField(field, originDrugstore);
			
			ReflectionUtils.setField(field, storedDrugstore, newValue);
		});
	}
}
