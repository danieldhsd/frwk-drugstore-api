package com.drugstore.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
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

import com.drugstore.api.controller.assembler.CategoryInputDisassembler;
import com.drugstore.api.controller.assembler.CategoryModelAssembler;
import com.drugstore.api.controller.model.CategoryModel;
import com.drugstore.api.controller.model.input.CategoryInput;
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
	
	@Autowired
	private CategoryModelAssembler categoryModelAssembler;
	
	@Autowired
	private CategoryInputDisassembler categoryInputDisassembler;
	
	@GetMapping
	public List<CategoryModel> getAll() {
		return categoryModelAssembler.toCollectionModel(categoryRepository.findAll());
	}
	
	@GetMapping("/{idCategory}")
	public CategoryModel findById(@PathVariable Long idCategory) {
		Category category = categoryService.getOrThrowException(idCategory);
		
		return categoryModelAssembler.toModel(category);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CategoryModel create(@RequestBody @Valid CategoryInput categoryInput) {
		Category category = categoryInputDisassembler.toDomainObject(categoryInput);
		category = categoryService.create(category);

		return categoryModelAssembler.toModel(category);
	}
	
	@PutMapping("/{idCategory}")
	public CategoryModel update(@PathVariable Long idCategory, @RequestBody @Valid CategoryInput categoryInput) throws Exception {
		Category category = categoryInputDisassembler.toDomainObject(categoryInput);

		Category storedCategory = categoryService.getOrThrowException(idCategory);
		
		BeanUtils.copyProperties(category, storedCategory, "id", "createdAt", "updatedAt");
		
		return categoryModelAssembler.toModel(categoryService.create(storedCategory));
	}
	
	@DeleteMapping("/{idCategory}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long idCategory) {
		categoryService.remove(idCategory);
	}
}
