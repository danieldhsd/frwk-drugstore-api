package com.drugstore.api.controller.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.drugstore.api.controller.model.input.CategoryInput;
import com.drugstore.api.domain.model.Category;

@Component
public class CategoryInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Category toDomainObject(CategoryInput categoryInput) {
		return modelMapper.map(categoryInput, Category.class);
	}
	
	public void copyToDomainObject(CategoryInput categoryInput, Category category) {
		modelMapper.map(categoryInput, category);
	}
	
}
