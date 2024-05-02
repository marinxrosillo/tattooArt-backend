package com.tattooart.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tattooart.domain.repositories.UserRepository;
import com.tattooart.persistence.entities.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository
			.getByEmail(email)
			.orElseThrow(() -> new UsernameNotFoundException("El usuario con email " + email + " no existe"));
	
		return new UserDetailsImpl(user);
	}
}