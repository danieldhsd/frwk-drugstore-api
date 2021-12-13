package com.drugstore.api.controller.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.drugstore.api.controller.model.input.UserInput;
import com.drugstore.api.domain.model.User;

@Component
public class UserInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public User toDomainObject(UserInput userInput) {
		return modelMapper.map(userInput, User.class);
	}
	
	public void copyToDomainObject(UserInput userInput, User user) {
		modelMapper.map(userInput, user);
	}
}
