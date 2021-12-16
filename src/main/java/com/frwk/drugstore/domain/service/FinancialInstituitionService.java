package com.frwk.drugstore.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.frwk.drugstore.domain.exception.EntityAlreadyExistsException;
import com.frwk.drugstore.domain.exception.EntityInUseException;
import com.frwk.drugstore.domain.exception.EntityNotFoundException;
import com.frwk.drugstore.domain.model.FinancialInstituition;
import com.frwk.drugstore.domain.repository.FinancialInstituitionRepository;

@Service
public class FinancialInstituitionService {

	@Autowired
	private FinancialInstituitionRepository financialInstituitionRepository;
	
	public FinancialInstituition create(FinancialInstituition financialInstituition) {
		if(isFinancialInstituitionAlreadyExists(financialInstituition.getCode(), financialInstituition.getId())) {
			throw new EntityAlreadyExistsException("Financial Instituition already exists");
		}
		
		return financialInstituitionRepository.save(financialInstituition);
	}
	
	public void remove(Long idFinancialInstituition) {
		try {
			financialInstituitionRepository.deleteById(idFinancialInstituition);
		
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException("Financial Instituition not found");
			
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException("Financial Instituition can't be removed.");
		}
	}
	
	public FinancialInstituition getOrThrowException(Long idFinancialInstituition) {
		return financialInstituitionRepository.findById(idFinancialInstituition).orElseThrow(() -> 
						new EntityNotFoundException("Financial Instituition not found!"));
	}
	
	private boolean isFinancialInstituitionAlreadyExists(String code, Long id) {
		if(id != null) {
			return financialInstituitionRepository.countByCodeAndIdDiff(code, id) > 0;
		}
		
		return financialInstituitionRepository.existsByCode(code);
	}
}
