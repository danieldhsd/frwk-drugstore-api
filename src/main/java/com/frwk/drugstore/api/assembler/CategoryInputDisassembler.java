package com.frwk.drugstore.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.frwk.drugstore.api.model.input.CategoryInput;
import com.frwk.drugstore.domain.model.Category;

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
