package com.frwk.drugstore.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.frwk.drugstore.api.model.input.ProductInput;
import com.frwk.drugstore.domain.model.Category;
import com.frwk.drugstore.domain.model.Product;

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
