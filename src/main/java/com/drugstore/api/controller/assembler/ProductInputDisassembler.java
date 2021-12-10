package com.drugstore.api.controller.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.drugstore.api.controller.model.input.ProductInput;
import com.drugstore.api.domain.model.Category;
import com.drugstore.api.domain.model.Product;

@Component
public class ProductInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Product toDomainObject(ProductInput productInput) {
		return modelMapper.map(productInput, Product.class);
	}
	
	public void copyToDomainObject(ProductInput productInput, Product product) {
		product.setCategory(new Category());
		
		modelMapper.map(productInput, product);
	}
}
