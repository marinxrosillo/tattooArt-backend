package com.tattooart.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tattooart.domain.repositories.AppointmentRepository;
import com.tattooart.persistence.entities.Appointment;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;
	
	public List<Appointment> findAll() {
		return appointmentRepository.findAll();
	}
	
	public Optional<Appointment> findById(int id) {
		return appointmentRepository.findById(id);
	}
	
	public Appointment save(Appointment appointment) {
		return appointmentRepository.save(appointment);
	}	
	
	public boolean deleteById(int id) {
		return findById(id).map(user -> {
			appointmentRepository.deleteById(id);
			return true;
		}).orElse(false);
	}
}
