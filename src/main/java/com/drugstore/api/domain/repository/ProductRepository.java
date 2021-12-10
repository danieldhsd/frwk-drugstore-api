package com.drugstore.api.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.drugstore.api.domain.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	public List<Product> findBySku(String sku);
	
	@Query("FROM Product p WHERE p.sku = :sku AND p.id != :id")
	public List<Product> findBySkuAndIdDiff(String sku, Long id);
}
