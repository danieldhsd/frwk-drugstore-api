package com.frwk.drugstore.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.frwk.drugstore.domain.model.AccountFinancialInstituition;

@Repository
public interface AccountFinancialInstituitionRepository extends JpaRepository<AccountFinancialInstituition, Long>{

	@Query("FROM AccountFinancialInstituition acc WHERE acc.client.id = :idUser")
	List<AccountFinancialInstituition> findAllByIdUser(Long idUser);
}
