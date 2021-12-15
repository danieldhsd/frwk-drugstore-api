package com.frwk.drugstore.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.frwk.drugstore.domain.model.FinancialInstituition;

@Repository
public interface FinancialInstituitionRepository extends JpaRepository<FinancialInstituition, Long> {

	boolean existsByCode(String code);
	
	@Query("SELECT count(*) FROM FinancialInstituition f WHERE f.code = :code AND f.id != :id")
	int countByCodeAndIdDiff(String code, Long id);
}
