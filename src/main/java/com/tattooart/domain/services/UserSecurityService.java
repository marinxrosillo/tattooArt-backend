package com.tattooart.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.tattooart.domain.repositories.UserRepository;
import com.tattooart.persistence.entities.User;

@Service
public class UserSecurityService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = this.userRepository.getByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));
		
//		String roles[] = new String[user.getRoles().size()];
//		
//		for(int i=0; i<roles.length; i++) {
//			roles[i] = user.getRoles().get(i).getRole();	
//		}
		
		return org.springframework.security.core.userdetails.User.builder()
				.username(user.getUsername())
				.password(user.getPassword())
//				.roles(roles)
				.disabled(user.isDisabled())
				.accountLocked(user.isLocked())
				.build();
	}
}