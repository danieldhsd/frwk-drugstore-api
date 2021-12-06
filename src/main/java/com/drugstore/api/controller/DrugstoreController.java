package com.drugstore.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drugstore.api.domain.exception.DrugstoreAlreadyExistsException;
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
	
	@GetMapping
	public List<Drugstore> getAll() {
		return drugstoreRepository.findAll();
	}
	
	@GetMapping("/{idDrugstore}")
	public ResponseEntity<?> findById(@PathVariable Long idDrugstore) {
		Optional<Drugstore> drugstoreOpt = drugstoreRepository.findById(idDrugstore);
		
		if(drugstoreOpt.isPresent()) {
			return ResponseEntity.ok(drugstoreOpt.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody @Valid Drugstore drugstore) {
		try {
			drugstore = drugstoreService.create(drugstore);
			return ResponseEntity.status(HttpStatus.CREATED).body(drugstore);
		
		} catch (DrugstoreAlreadyExistsException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{idDrugstore}")
	public ResponseEntity<?> update(@PathVariable Long idDrugstore, @RequestBody Map<String, Object> fields) {
		Optional<Drugstore> drugstoreOpt = drugstoreRepository.findById(idDrugstore);
		
		if(!drugstoreOpt.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Drugstore storedDrugstore = drugstoreOpt.get();
		
		merge(fields, storedDrugstore);
		
		return create(storedDrugstore);
	}
	
	@DeleteMapping("/{idDrugstore}")
	public ResponseEntity<?> delete(@PathVariable Long idDrugstore) {
		try {
			drugstoreRepository.deleteById(idDrugstore);
			return ResponseEntity.noContent().build();

		} catch (EmptyResultDataAccessException e) {
			return ResponseEntity.notFound().build();
			
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Drugstore can't be removed.");
		}
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
