package com.frwk.drugstore.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.frwk.drugstore.domain.exception.EntityInUseException;
import com.frwk.drugstore.domain.exception.EntityNotFoundException;
import com.frwk.drugstore.domain.exception.GenericException;
import com.frwk.drugstore.domain.model.User;
import com.frwk.drugstore.domain.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User create(User user) {
		if(isEmailOrCpfAlreadyInUse(user.getEmail(), user.getCpf(), user.getId())) {
			throw new GenericException("This Email and/or CPF is already in use");
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
	
	private boolean isEmailOrCpfAlreadyInUse(String userEmail, String userCpf, Long userId) {
		if(userId != null) {
			return userRepository.countByEmailOrCpfAndIdDiff(userEmail, userCpf, userId) > 0;
		}
		
		return userRepository.existsByEmailOrCpf(userEmail, userCpf);
	}
	
}
