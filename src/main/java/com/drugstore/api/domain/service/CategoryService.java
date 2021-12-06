package com.drugstore.api.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drugstore.api.domain.exception.CategoryAlreadyExistsException;
import com.drugstore.api.domain.model.Category;
import com.drugstore.api.domain.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public Category create(Category category) {
		List<Category> categories = categoryRepository.findByName(category.getName());
		
		if(categories != null && !categories.isEmpty()) {
			throw new CategoryAlreadyExistsException("Category already exists");
		}
		
		return categoryRepository.save(category);
	}
	
}
