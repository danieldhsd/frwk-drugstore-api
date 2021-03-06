package com.frwk.drugstore.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.frwk.drugstore.domain.exception.EntityAlreadyExistsException;
import com.frwk.drugstore.domain.exception.EntityInUseException;
import com.frwk.drugstore.domain.exception.EntityNotFoundException;
import com.frwk.drugstore.domain.model.Category;
import com.frwk.drugstore.domain.model.Product;
import com.frwk.drugstore.domain.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryService categoryService;
	
	public Product create(Product product) {
		if(isProductAlreadyExists(product)) {
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
	
	private boolean isProductAlreadyExists(Product product) {
		if(product.getId() != null) {
			return productRepository.countBySkuAndIdDiff(product.getSku(), product.getId()) > 0;
		}
		
		return productRepository.existsBySku(product.getSku());
	}
}
