package com.drugstore.api.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.drugstore.api.domain.model.Drugstore;

@Repository
public interface DrugstoreRepository extends JpaRepository<Drugstore, Long> {

	public List<Drugstore> findByCnpj(String cnpj);
	
	@Query("FROM Drugstore d WHERE d.cnpj = :cnpj AND d.id != :id")
	public List<Drugstore> findByCnpjAndIdDiff(String cnpj, Long id);
}
