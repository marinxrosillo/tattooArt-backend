package com.tattooart.web.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tattooart.domain.services.UserService;
import com.tattooart.persistence.entities.User;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<User> findById(@PathVariable("userId") int userId) {
		return userService.findById(userId)
				.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping
	public ResponseEntity<User> create(@RequestBody User user) {
		return new ResponseEntity<User>(userService.save(user), HttpStatus.CREATED);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<User> update(@PathVariable("userId") int userId, @RequestBody User user) {
		if (userId != user.getId()) {
	        return ResponseEntity.badRequest().build();
	    }

	    Optional<User> userOptional = userService.findById(userId);
	    if (userOptional.isPresent()) {
	        User updatedUser = userService.save(user);
	        return ResponseEntity.ok(updatedUser);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<User> delete(@PathVariable("userId") int userId) {
		if(userService.deleteById(userId)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}