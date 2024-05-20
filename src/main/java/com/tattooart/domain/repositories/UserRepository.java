package com.tattooart.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tattooart.persistence.crud.UserCrudRepository;
import com.tattooart.persistence.entities.User;

@Repository
public class UserRepository {

	@Autowired
	private UserCrudRepository userCrudRepository;
	
	public List<User> findAll() {
		return (List<User>) userCrudRepository.findAll();
	}
	
	public Optional<User> findById(int id) {
		return userCrudRepository.findById(id);
	}
	
	public User save(User user)  {
		return userCrudRepository.save(user);
	}
	
	public void deleteById(int id) {
		userCrudRepository.deleteById(id);
	}
	
	public Optional<User> getByUsername(String username) {
		return userCrudRepository.getByUsername(username);
	}
	
	public Optional<User> getByEmail(String email) {
		return userCrudRepository.findByEmail(email);
	}
}
