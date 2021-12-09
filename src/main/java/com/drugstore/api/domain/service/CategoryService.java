package com.drugstore.api.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drugstore.api.domain.exception.EntityAlreadyExistsException;
import com.drugstore.api.domain.model.Category;
import com.drugstore.api.domain.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public Category create(Category category) {
		List<Category> categories = searchExistingCategories(category);
		
		if(categories != null && !categories.isEmpty()) {
			throw new EntityAlreadyExistsException("Category already exists");
		}
		
		return categoryRepository.save(category);
	}
	
	private List<Category> searchExistingCategories(Category category) {
		if(category.getId() != null) {
			return categoryRepository.findByNameAndIdDiff(category.getName(), category.getId());
		}
		
		return categoryRepository.findByName(category.getName());
	}
	
}
