package com.drugstore.api.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drugstore.api.domain.exception.EntityAlreadyExistsException;
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
	
	private List<Drugstore> searchExistingDrugstores(Drugstore drugstore) {
		if(drugstore.getId() != null) {
			return drugstoreRepository.findByCnpjAndIdDiff(drugstore.getCnpj(), drugstore.getId());
		}
		
		return drugstoreRepository.findByCnpj(drugstore.getCnpj());
	}
	
}
