package com.drugstore.api.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.drugstore.api.domain.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	public List<Category> findByName(String name);
}
