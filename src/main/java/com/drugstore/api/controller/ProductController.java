package com.drugstore.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.drugstore.api.controller.assembler.ProductInputDisassembler;
import com.drugstore.api.controller.assembler.ProductModelAssembler;
import com.drugstore.api.controller.model.ProductModel;
import com.drugstore.api.controller.model.input.ProductInput;
import com.drugstore.api.domain.model.Product;
import com.drugstore.api.domain.repository.ProductRepository;
import com.drugstore.api.domain.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductModelAssembler productModelAssembler;
	
	@Autowired
	private ProductInputDisassembler productInputDisassembler;
	
	@Value("product.photos.base.path")
	private String productPhotosBasePath;
	
	@GetMapping
	public List<ProductModel> getAll() {
		return productModelAssembler.toCollectionModel(productRepository.findAll());
	}
	
	@GetMapping("/{idProduct}")
	public ProductModel findById(@PathVariable Long idProduct) {
		Product product = productService.getOrThrowException(idProduct);
		
		return productModelAssembler.toModel(product);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProductModel create(@RequestBody ProductInput productInput/*, @RequestParam MultipartFile productPhoto*/) {
//		TODO: Implementar a parte de salvar a foto no disco
		Product product = productInputDisassembler.toDomainObject(productInput);
		product = productService.create(product);
		
		return productModelAssembler.toModel(product);
	}
	
	@PutMapping("/{idProduct}")
	public ProductModel update(@PathVariable Long idProduct, @RequestBody @Valid ProductInput productInput) {
		Product product = productService.getOrThrowException(idProduct);
		productInputDisassembler.copyToDomainObject(productInput, product);
		
		return productModelAssembler.toModel(productService.create(product));
	}
	
	@DeleteMapping("/{idProduct}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long idProduct) {
		productService.remove(idProduct);
	}
}
