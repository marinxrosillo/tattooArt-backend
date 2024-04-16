package com.tattooart.persistence.crud;

import org.springframework.data.repository.CrudRepository;

import com.tattooart.persistence.entities.Tattooist;

public interface TattooistCrudRepository extends CrudRepository<Tattooist, Integer> {

}
