package com.drugstore.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drugstore.api.domain.exception.CategoryAlreadyExistsException;
import com.drugstore.api.domain.model.Category;
import com.drugstore.api.domain.repository.CategoryRepository;
import com.drugstore.api.domain.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public List<Category> getAll() {
		return categoryRepository.findAll();
	}
	
	@GetMapping("/{idCategory}")
	public ResponseEntity<?> findById(@PathVariable Long idCategory) {
		Optional<Category> categoryOpt = categoryRepository.findById(idCategory);
		
		if(categoryOpt.isPresent()) {
			return ResponseEntity.ok(categoryOpt.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody @Valid Category category) {
		try {
			category = categoryService.create(category);
			return ResponseEntity.status(HttpStatus.CREATED).body(category);
		
		} catch (CategoryAlreadyExistsException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{idCategory}")
	public ResponseEntity<?> update(@PathVariable Long idCategory, @RequestBody @Valid Category category) {
		Optional<Category> categoryOpt = categoryRepository.findById(idCategory);
		
		if(!categoryOpt.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Category storedCategory = categoryOpt.get();
		BeanUtils.copyProperties(category, storedCategory, "id", "createdAt", "updatedAt");
		
		storedCategory = categoryService.create(storedCategory);
		
		return ResponseEntity.ok(storedCategory);
	}
	
	@DeleteMapping("/{idCategory}")
	public ResponseEntity<?> delete(@PathVariable Long idCategory) {
		try {
			categoryRepository.deleteById(idCategory);
			return ResponseEntity.noContent().build();

		} catch (EmptyResultDataAccessException e) {
			return ResponseEntity.notFound().build();
			
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Category can't be removed.");
		}
	}
}
