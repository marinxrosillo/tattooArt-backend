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

import com.tattooart.domain.services.TattooistService;
import com.tattooart.persistence.entities.Tattooist;

@RestController
@RequestMapping("/tattooists")
public class TattooistController {

	@Autowired
	private TattooistService tattooistService;

	@GetMapping
	public ResponseEntity<List<Tattooist>> findAll() {
		return new ResponseEntity<List<Tattooist>>(tattooistService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{tattooistId}")
	public ResponseEntity<Tattooist> findById(@PathVariable("tattooistId") int tattooistId) {
		return tattooistService.findById(tattooistId).map(tattooist -> new ResponseEntity<>(tattooist, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	public ResponseEntity<Tattooist> create(@RequestBody Tattooist tattooist) {
		return new ResponseEntity<Tattooist>(tattooistService.save(tattooist), HttpStatus.CREATED);
	}

	@PutMapping("/{tattooistId}")
	public ResponseEntity<Tattooist> update(@PathVariable("tattooistId") int tattooistId, @RequestBody Tattooist tattooist) {
		if (tattooistId != tattooist.getId()) {
	        return ResponseEntity.badRequest().build();	
	    }

	    Optional<Tattooist> tattooistOptional = tattooistService.findById(tattooistId);
	    if (tattooistOptional.isPresent()) {
	    	Tattooist updatedTattooist = tattooistService.save(tattooist);
	        return ResponseEntity.ok(updatedTattooist);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

	@DeleteMapping("/{tattooistId}")
	public ResponseEntity<Tattooist> delete(@PathVariable("tattooistId") int tattooistId) {
		if (tattooistService.deleteById(tattooistId)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}