package com.frwk.drugstore.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.frwk.drugstore.domain.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	boolean existsByName(String name);
	
	@Query("SELECT count(*) FROM Category c WHERE c.name = :name AND c.id != :id")
	int countByNameAndIdDiff(String name, Long id);
}
