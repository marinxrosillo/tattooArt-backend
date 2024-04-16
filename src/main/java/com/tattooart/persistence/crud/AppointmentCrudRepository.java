package com.tattooart.persistence.crud;

import org.springframework.data.repository.CrudRepository;

import com.tattooart.persistence.entities.Appointment;

public interface AppointmentCrudRepository extends CrudRepository<Appointment, Integer> {

}
