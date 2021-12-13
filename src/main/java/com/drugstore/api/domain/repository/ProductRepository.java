package com.drugstore.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.drugstore.api.domain.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	boolean existsBySku(String sku);
	
	@Query("SELECT count(*) FROM Product p WHERE p.sku = :sku AND p.id != :id")
	int countBySkuAndIdDiff(String sku, Long id);
}
