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
		// Buscar un usuario por su email utilizando el servicio userService
		Optional<User> user = userService.findByEmail(appointmentDto.getUser());

		// Verificar si el usuario existe (isPresent)
		if (user.isPresent()) {
			// Si el usuario existe, establecer los detalles del appointment
			appointment.setDate(appointmentDto.getDate());
			appointment.setTime(appointmentDto.getTime());
			appointment.setStatus(appointmentDto.getStatus());
			appointment.setUser(user.get()); // Establecer el usuario encontrado
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
