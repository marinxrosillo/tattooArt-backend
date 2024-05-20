package com.tattooart.persistence.crud;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.tattooart.persistence.entities.User;

public interface UserCrudRepository extends CrudRepository<User, Integer> {

	Optional<User> getByUsername(String username);
	
	Optional<User> findByEmail(String email);
}
