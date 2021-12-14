package com.frwk.drugstore.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.frwk.drugstore.api.model.CategoryModel;
import com.frwk.drugstore.domain.model.Category;

@Component
public class CategoryModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public CategoryModel toModel(Category category) {
		return modelMapper.map(category, CategoryModel.class);
	}
	
	public List<CategoryModel> toCollectionModel(List<Category> categories) {
		return categories.stream()
				.map(category -> toModel(category))
				.collect(Collectors.toList());
	}
}
