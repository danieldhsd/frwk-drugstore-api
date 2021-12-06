package com.drugstore.api.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drugstore.api.domain.exception.DrugstoreAlreadyExistsException;
import com.drugstore.api.domain.model.Drugstore;
import com.drugstore.api.domain.repository.DrugstoreRepository;

@Service
public class DrugstoreService {

	@Autowired
	private DrugstoreRepository drugstoreRepository;
	
	public Drugstore create(Drugstore drugstore) {
		List<Drugstore> drugstores = null;
		
		if(drugstore.getId() != null) {
			drugstores = drugstoreRepository.findByCnpjAndIdDiff(drugstore.getCnpj(), drugstore.getId());
			
		} else {
			drugstores = drugstoreRepository.findByCnpj(drugstore.getCnpj());
		}
		
		if(drugstores != null && !drugstores.isEmpty()) {
			throw new DrugstoreAlreadyExistsException("Drugstore already exists");
		}
		
		return drugstoreRepository.save(drugstore);
	}
	
}
