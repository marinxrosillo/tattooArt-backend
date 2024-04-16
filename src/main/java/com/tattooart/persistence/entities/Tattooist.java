package com.tattooart.persistence.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tattooist")
public class Tattooist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name", length = 255)
	private String name;

	@Column(name = "speciality", length = 255)
	private String speciality;

	@Column(name = "availability")
	private Boolean availability;

	@JsonIgnore
	@OneToMany(mappedBy = "tattooIst", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Appointment> appointments = new ArrayList<>();

	// Getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public Boolean getAvailability() {
		return availability;
	}

	public void setAvailability(Boolean availability) {
		this.availability = availability;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public void addAppointment(Appointment appointment) {
		appointments.add(appointment);
		appointment.setTattooIst(this);
	}

	public void removeAppointment(Appointment appointment) {
		appointments.remove(appointment);
		appointment.setTattooIst(null);
	}
}
