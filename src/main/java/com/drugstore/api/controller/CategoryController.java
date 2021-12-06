package com.drugstore.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drugstore.api.domain.exception.AlreadyExistingCategoryException;
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
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody @Valid Category category) throws Exception {
		try {
			category = categoryService.create(category);
			return ResponseEntity.status(HttpStatus.CREATED).body(category);
		
		} catch (AlreadyExistingCategoryException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
