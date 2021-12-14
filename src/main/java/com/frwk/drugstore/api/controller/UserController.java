package com.frwk.drugstore.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.frwk.drugstore.api.assembler.UserInputDisassembler;
import com.frwk.drugstore.api.assembler.UserModelAssembler;
import com.frwk.drugstore.api.model.UserModel;
import com.frwk.drugstore.api.model.input.PasswordInput;
import com.frwk.drugstore.api.model.input.UserInput;
import com.frwk.drugstore.api.model.input.UserInputWithPassword;
import com.frwk.drugstore.domain.model.User;
import com.frwk.drugstore.domain.repository.UserRepository;
import com.frwk.drugstore.domain.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserModelAssembler userModelAssembler;
	
	@Autowired
	private UserInputDisassembler userInputDisassembler;
	
	@GetMapping
	public List<UserModel> getAll() {
		return userModelAssembler.toCollectionModel(userRepository.findAll());
	}
	
	@GetMapping("/{idUser}")
	public UserModel findById(@PathVariable Long idUser) {
		User user = userService.getOrThrowException(idUser);
		
		return userModelAssembler.toModel(user);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserModel create(@RequestBody @Valid UserInputWithPassword userInput) {
		User user = userInputDisassembler.toDomainObject(userInput);
		user = userService.create(user);
		
		return userModelAssembler.toModel(user);
	}
	
	@PutMapping("/{idUser}/password")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void changePassword(@PathVariable Long idUser, @RequestBody @Valid PasswordInput passwordInput) {
		userService.changePassword(idUser, passwordInput.getCurrentPassword(), passwordInput.getNewPassword());
	}
	
	@PutMapping("/{idUser}")
	public UserModel update(@PathVariable Long idUser, @RequestBody @Valid UserInput userInput) {
		User user = userService.getOrThrowException(idUser);
		userInputDisassembler.copyToDomainObject(userInput, user);
		
		return userModelAssembler.toModel(userService.create(user));
	}
	
	@DeleteMapping("/{idUser}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long idUser) {
		userService.remove(idUser);
	}
}
