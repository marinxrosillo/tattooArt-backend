package com.tattooart.web.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tattooart.domain.dto.AppointmentDto;
import com.tattooart.domain.services.AppointmentService;
import com.tattooart.persistence.entities.Appointment;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	@GetMapping
	public ResponseEntity<List<Appointment>> findAll() {
		return new ResponseEntity<List<Appointment>>(appointmentService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{appointmentId}")
	public ResponseEntity<Appointment> findById(@PathVariable("appointmentId") int appointmentId) {
		return appointmentService.findById(appointmentId).map(appointment -> new ResponseEntity<>(appointment, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	public ResponseEntity<Appointment> create(@RequestBody AppointmentDto appointment) {
		return new ResponseEntity<Appointment>(appointmentService.reserve(appointment), HttpStatus.CREATED);
	}

	@PutMapping("/{appointmentId}")
	public ResponseEntity<Appointment> update(@PathVariable("appointmentId") int appointmentId, @RequestBody Appointment appointment) {
		if (appointmentId != appointment.getId()) {
	        return ResponseEntity.badRequest().build();
	    }

	    Optional<Appointment> appointmentOptional = appointmentService.findById(appointmentId);
	    if (appointmentOptional.isPresent()) {
	    	Appointment updatedAppointment = appointmentService.save(appointment);
	        return ResponseEntity.ok(updatedAppointment);
	    } else {	
	        return ResponseEntity.notFound().build();
	    }
	}

	@DeleteMapping("/{appointmentId}")
	public ResponseEntity<Appointment> delete(@PathVariable("appointmentId") int appointmentId) {
		if (appointmentService.deleteById(appointmentId)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}