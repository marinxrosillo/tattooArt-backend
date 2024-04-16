package com.tattooart.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tattooart.domain.repositories.UserRepository;
import com.tattooart.persistence.entities.User;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public Optional<User> findById(int id) {
		return userRepository.findById(id);
	}
	
	public User save(User user) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        return userRepository.save(user);
    }
	
	public boolean deleteById(int id) {
		return findById(id).map(user -> {
			userRepository.deleteById(id);
			return true;
		}).orElse(false);
	}
}
