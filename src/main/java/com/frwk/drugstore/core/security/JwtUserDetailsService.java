package com.frwk.drugstore.core.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.frwk.drugstore.domain.model.User;
import com.frwk.drugstore.domain.repository.UserRepository;



@Component
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findFirstByCpf(cpf);
		if(!user.isPresent()) {
			throw new UsernameNotFoundException("Usuário [ "+ cpf + " ] não encontrado.");
		}
		
		return new DetailUserDate(user);
	}
}

