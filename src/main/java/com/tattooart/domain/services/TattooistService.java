package com.tattooart.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tattooart.domain.repositories.TattooistRepository;
import com.tattooart.persistence.entities.Tattooist;

@Service
public class TattooistService {

	@Autowired
	private TattooistRepository tattooistRepository;
	
	public List<Tattooist> findAll() {
		return tattooistRepository.findAll();
	}
	
	public Optional<Tattooist> findById(int id) {
		return tattooistRepository.findById(id);
	}
	
	public Tattooist save(Tattooist tattooist) {	
		return tattooistRepository.save(tattooist);
	}	
	
	public boolean deleteById(int id) {
		return findById(id).map(user -> {
			tattooistRepository.deleteById(id);
			return true;
		}).orElse(false);
	}
}
