package com.tattooart.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tattooart.persistence.crud.TattooistCrudRepository;
import com.tattooart.persistence.entities.Tattooist;

@Repository
public class TattooistRepository {

	@Autowired
	private TattooistCrudRepository tattooistCrudRepository;

	public List<Tattooist> findAll() {
		return (List<Tattooist>) tattooistCrudRepository.findAll();
	}

	public Optional<Tattooist> findById(int id) {
		return tattooistCrudRepository.findById(id);
	}

	public Tattooist save(Tattooist tattooist) {
		return tattooistCrudRepository.save(tattooist);
	}

	public void deleteById(int id) {
		tattooistCrudRepository.deleteById(id);
	}
}