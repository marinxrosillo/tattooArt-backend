package com.tattooart.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tattooart.domain.dto.AppointmentDto;
import com.tattooart.domain.repositories.AppointmentRepository;
import com.tattooart.persistence.entities.Appointment;
import com.tattooart.persistence.entities.User;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private UserService userService;
	
	public List<Appointment> findAll() {
		return appointmentRepository.findAll();
	}
	
	public Optional<Appointment> findById(int id) {
		return appointmentRepository.findById(id);
	}
	
	public Appointment save(Appointment appointment) {
		return appointmentRepository.save(appointment);
	}
	
	public Appointment reserve(AppointmentDto appointmentDto) {
		Appointment appointment = new Appointment();
		Optional<User> user = userService.findByEmail(appointmentDto.getUser());
		
		if (user.isPresent()) {
			appointment.setDate(appointmentDto.getDate());
			appointment.setTime(appointmentDto.getTime());
			appointment.setStatus(appointmentDto.getStatus());
			appointment.setUser(user.get());
			appointment.setTattooList(appointmentDto.getTattooList());
			appointment.setTattooIst(appointmentDto.getTattooIst());
	    }
		
		return appointmentRepository.save(appointment);
	}
	
	public boolean deleteById(int id) {
		return findById(id).map(user -> {
			appointmentRepository.deleteById(id);
			return true;
		}).orElse(false);
	}
}
