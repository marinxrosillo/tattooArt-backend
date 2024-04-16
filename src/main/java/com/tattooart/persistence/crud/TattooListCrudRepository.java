package com.tattooart.persistence.crud;

import org.springframework.data.repository.CrudRepository;

import com.tattooart.persistence.entities.TattooList;

public interface TattooListCrudRepository extends CrudRepository<TattooList, Integer> {

}
