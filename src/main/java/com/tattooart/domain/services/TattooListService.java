package com.tattooart.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tattooart.domain.repositories.TattooListRepository;
import com.tattooart.persistence.entities.TattooList;

@Service
public class TattooListService {

	@Autowired
	private TattooListRepository tattooListRepository;
	
	public List<TattooList> findAll() {
		return tattooListRepository.findAll();
	}
	
	public Optional<TattooList> findById(int id) {
		return tattooListRepository.findById(id);
	}
	
	public TattooList save(TattooList tattooList) {	
		return tattooListRepository.save(tattooList);
	}	
	
	public boolean deleteById(int id) {	
		return findById(id).map(user -> {
			tattooListRepository.deleteById(id);
			return true;
		}).orElse(false);
	}
}
