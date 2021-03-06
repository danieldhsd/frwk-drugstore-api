package com.frwk.drugstore.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.frwk.drugstore.domain.model.Drugstore;

@Repository
public interface DrugstoreRepository extends JpaRepository<Drugstore, Long> {

	boolean existsByCnpj(String cnpj);
	
	@Query("SELECT count(*) FROM Drugstore d WHERE d.cnpj = :cnpj AND d.id != :id")
	int countByCnpjAndIdDiff(String cnpj, Long id);
}
