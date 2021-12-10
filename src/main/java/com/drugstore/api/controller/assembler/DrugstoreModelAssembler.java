package com.drugstore.api.controller.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.drugstore.api.controller.model.DrugstoreModel;
import com.drugstore.api.domain.model.Drugstore;

@Component
public class DrugstoreModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public DrugstoreModel toModel(Drugstore drugstore) {
		return modelMapper.map(drugstore, DrugstoreModel.class);
	}
	
	public List<DrugstoreModel> toCollectionModel(List<Drugstore> drugstores) {
		return drugstores.stream()
				.map(category -> toModel(category))
				.collect(Collectors.toList());
	}
}
