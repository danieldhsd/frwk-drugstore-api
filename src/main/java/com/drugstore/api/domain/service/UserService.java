package com.drugstore.api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.drugstore.api.domain.exception.EntityInUseException;
import com.drugstore.api.domain.exception.EntityNotFoundException;
import com.drugstore.api.domain.exception.GenericException;
import com.drugstore.api.domain.model.User;
import com.drugstore.api.domain.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User create(User user) {
		if(isEmailAlreadyInUse(user.getEmail(), user.getId())) {
			throw new GenericException("This email is already in use");
		}
		
		if(isCpfAlreadyInUse(user.getCpf(), user.getId())) {
			throw new GenericException("This CPF is already in use");
		}
		
		if(user.isNew()) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		
		return userRepository.save(user);
	}
	
	public void changePassword(Long idUser, String currentPassword, String newPassword) {
		User user = getOrThrowException(idUser);
		
		if(!passwordEncoder.matches(currentPassword, user.getPassword())) {
			throw new GenericException("The current password is incorrect.");
		}
		
		user.setPassword(passwordEncoder.encode(newPassword));
		userRepository.save(user);
	}
	
	public void remove(Long idUser) {
		try {
			userRepository.deleteById(idUser);
		
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException("User not found");
			
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException("User can't be removed.");
		}
	}
	
	public User getOrThrowException(Long idUser) {
		return userRepository.findById(idUser).orElseThrow(() -> new EntityNotFoundException("User not found!"));
	}
	
	private boolean isEmailAlreadyInUse(String userEmail, Long userId) {
		if(userId != null) {
			return userRepository.countByEmailAndIdDiff(userEmail, userId) > 0;
		}
		
		return userRepository.existsByEmail(userEmail);
	}
	
	private boolean isCpfAlreadyInUse(String userCpf, Long userId) {
		if(userId != null) {
			return userRepository.countByCpfAndIdDiff(userCpf, userId) > 0;
		}
		
		return userRepository.existsByCpf(userCpf);
	}
}
