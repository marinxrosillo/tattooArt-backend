package com.tattooart.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tattooart.persistence.crud.TattooListCrudRepository;
import com.tattooart.persistence.entities.TattooList;

@Repository
public class TattooListRepository {

	@Autowired
	private TattooListCrudRepository tattoolistCrudRepository;

	public List<TattooList> findAll() {
		return (List<TattooList>) tattoolistCrudRepository.findAll();
	}

	public Optional<TattooList> findById(int id) {
		return tattoolistCrudRepository.findById(id);
	}

	public TattooList save(TattooList tattoolist) {
		return tattoolistCrudRepository.save(tattoolist);
	}

	public void deleteById(int id) {	
		tattoolistCrudRepository.deleteById(id);
	}
}
