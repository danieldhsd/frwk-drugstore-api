package com.drugstore.api.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.drugstore.api.domain.exception.EntityAlreadyExistsException;
import com.drugstore.api.domain.exception.EntityInUseException;
import com.drugstore.api.domain.exception.EntityNotFoundException;
import com.drugstore.api.domain.model.Category;
import com.drugstore.api.domain.model.Product;
import com.drugstore.api.domain.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryService categoryService;
	
	public Product create(Product product) {
		List<Product> products = searchExistingProducts(product);
		
		if(products != null && !products.isEmpty()) {
			throw new EntityAlreadyExistsException("Product already exists");
		}
		
		Category category = categoryService.getOrThrowException(product.getCategory().getId());
		product.setCategory(category);
		
		return productRepository.save(product);
	}
	
	public void remove(Long idProduct) {
		try {
			productRepository.deleteById(idProduct);
		
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException("Product not found");
			
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException("Product can't be removed.");
		}
	}
	
	public Product getOrThrowException(Long idProduct) {
		return productRepository.findById(idProduct).orElseThrow(() -> new EntityNotFoundException("Product not found!"));
	}
	
	private List<Product> searchExistingProducts(Product product) {
		if(product.getId() != null) {
			return productRepository.findBySkuAndIdDiff(product.getSku(), product.getId());
		}
		
		return productRepository.findBySku(product.getSku());
	}
}
