package com.drugstore.api.controller.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.drugstore.api.controller.model.UserModel;
import com.drugstore.api.domain.model.User;

@Component
public class UserModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public UserModel toModel(User user) {
		return modelMapper.map(user, UserModel.class);
	}
	
	public List<UserModel> toCollectionModel(List<User> users) {
		return users.stream()
				.map(category -> toModel(category))
				.collect(Collectors.toList());
	}
}
