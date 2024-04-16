package com.tattooart.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tattooart.persistence.crud.AppointmentCrudRepository;
import com.tattooart.persistence.entities.Appointment;

@Repository
public class AppointmentRepository {

	@Autowired
	private AppointmentCrudRepository appointmentCrudRepository;
	
	public List<Appointment> findAll() {
		return (List<Appointment>) appointmentCrudRepository.findAll();
	}
	
	public Optional<Appointment> findById(int id) {
		return appointmentCrudRepository.findById(id);
	}
	
	public Appointment save(Appointment appointment)  {
		return appointmentCrudRepository.save(appointment);
	}
	
	public void deleteById(int id) {
		appointmentCrudRepository.deleteById(id);	
	}
}
