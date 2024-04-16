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

import com.tattooart.domain.services.TattooListService;
import com.tattooart.persistence.entities.TattooList;

@RestController
@RequestMapping("/tattoolists")
public class TattooListController {

	@Autowired
	private TattooListService tattooListService;

	@GetMapping
	public ResponseEntity<List<TattooList>> findAll() {
		return new ResponseEntity<List<TattooList>>(tattooListService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{tattooListId}")
	public ResponseEntity<TattooList> findById(@PathVariable("tattooListId") int tattooListId) {
		return tattooListService.findById(tattooListId).map(tattooList -> new ResponseEntity<>(tattooList, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	public ResponseEntity<TattooList> create(@RequestBody TattooList tattooList) {
		return new ResponseEntity<TattooList>(tattooListService.save(tattooList), HttpStatus.CREATED);
	}

	@PutMapping("/{tattooListId}")
	public ResponseEntity<TattooList> update(@PathVariable("tattooListId") int tattooListId, @RequestBody TattooList tattooList) {
		if (tattooListId != tattooList.getId()) {
	        return ResponseEntity.badRequest().build();
	    }

	    Optional<TattooList> tattooListOptional = tattooListService.findById(tattooListId);
	    if (tattooListOptional.isPresent()) {
	    	TattooList updatedTattooList = tattooListService.save(tattooList);
	        return ResponseEntity.ok(updatedTattooList);
	    } else {
	        return ResponseEntity.notFound().build();	
	    }
	}

	@DeleteMapping("/{tattooListId}")
	public ResponseEntity<TattooList> delete(@PathVariable("tattooListId") int tattooListId) {
		if (tattooListService.deleteById(tattooListId)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}