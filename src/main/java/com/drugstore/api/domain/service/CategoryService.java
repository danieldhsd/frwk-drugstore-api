package com.drugstore.api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.drugstore.api.domain.exception.EntityAlreadyExistsException;
import com.drugstore.api.domain.exception.EntityInUseException;
import com.drugstore.api.domain.exception.EntityNotFoundException;
import com.drugstore.api.domain.model.Category;
import com.drugstore.api.domain.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public Category create(Category category) {
		if(isCategoryAlreadyExists(category)) {
			throw new EntityAlreadyExistsException("Category already exists");
		}
		
		return categoryRepository.save(category);
	}
	
	public void remove(Long idCategory) {
		try {
			categoryRepository.deleteById(idCategory);
		
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException("Category not found");
			
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException("Category can't be removed.");
		}
	}
	
	public Category getOrThrowException(Long idCategory) {
		return categoryRepository.findById(idCategory).orElseThrow(() -> new EntityNotFoundException("Category not found!"));
	}
	
	private boolean isCategoryAlreadyExists(Category category) {
		if(category.getId() != null) {
			return categoryRepository.countByNameAndIdDiff(category.getName(), category.getId()) > 0;
		}
		
		return categoryRepository.existsByName(category.getName());
	}
	
}
