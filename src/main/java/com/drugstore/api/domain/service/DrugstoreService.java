package com.drugstore.api.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.drugstore.api.domain.exception.EntityAlreadyExistsException;
import com.drugstore.api.domain.exception.EntityInUseException;
import com.drugstore.api.domain.exception.EntityNotFoundException;
import com.drugstore.api.domain.model.Drugstore;
import com.drugstore.api.domain.repository.DrugstoreRepository;

@Service
public class DrugstoreService {

	@Autowired
	private DrugstoreRepository drugstoreRepository;
	
	public Drugstore create(Drugstore drugstore) {
		List<Drugstore> drugstores = searchExistingDrugstores(drugstore);
		
		if(drugstores != null && !drugstores.isEmpty()) {
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
	
	private List<Drugstore> searchExistingDrugstores(Drugstore drugstore) {
		if(drugstore.getId() != null) {
			return drugstoreRepository.findByCnpjAndIdDiff(drugstore.getCnpj(), drugstore.getId());
		}
		
		return drugstoreRepository.findByCnpj(drugstore.getCnpj());
	}
	
}
