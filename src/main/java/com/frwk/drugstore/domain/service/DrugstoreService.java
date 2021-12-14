package com.frwk.drugstore.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.frwk.drugstore.domain.exception.EntityAlreadyExistsException;
import com.frwk.drugstore.domain.exception.EntityInUseException;
import com.frwk.drugstore.domain.exception.EntityNotFoundException;
import com.frwk.drugstore.domain.model.Drugstore;
import com.frwk.drugstore.domain.repository.DrugstoreRepository;

@Service
public class DrugstoreService {

	@Autowired
	private DrugstoreRepository drugstoreRepository;
	
	public Drugstore create(Drugstore drugstore) {
		if(isDrugstoreAlreadyExists(drugstore)) {
			throw new EntityAlreadyExistsException("Drugstore already exists");
		}
		
		return drugstoreRepository.save(drugstore);
	}
	
	public void remove(Long idDrugstore) {
		try {
			drugstoreRepository.deleteById(idDrugstore);
		
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException("Drugstore not found");
			
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException("Drugstore can't be removed.");
		}
	}
	
	public Drugstore getOrThrowException(Long idDrugstore) {
		return drugstoreRepository.findById(idDrugstore).orElseThrow(() -> new EntityNotFoundException("Drugstore not found!"));
	}
	
	private boolean isDrugstoreAlreadyExists(Drugstore drugstore) {
		if(drugstore.getId() != null) {
			return drugstoreRepository.countByCnpjAndIdDiff(drugstore.getCnpj(), drugstore.getId()) > 0;
		}
		
		return drugstoreRepository.existsByCnpj(drugstore.getCnpj());
	}
	
}
