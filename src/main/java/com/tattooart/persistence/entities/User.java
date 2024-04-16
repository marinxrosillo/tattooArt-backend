package com.tattooart.persistence.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 255)
	private String name;

	@Column(length = 255, unique = true)
	private String username;

	@Column(length = 255)
	private String password;

	@Column(length = 180, unique = true)
	private String email;

	@Column(length = 255)
	private String street;

	@Column(length = 255)
	private String number;

	@Column(length = 255)
	private String zipCode;

	@Column
	private boolean locked;

	@Column
	private boolean disabled;

	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private List<User_Role> roles;

	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Appointment> appointments;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public List<User_Role> getRoles() {
		return roles;
	}

	public void setRoles(List<User_Role> roles) {
		this.roles = roles;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}	

	public void addAppointment(Appointment appointment) {
		appointments.add(appointment);
		appointment.setUser(this);
	}

	public void removeAppointment(Appointment appointment) {
		appointments.remove(appointment);
		appointment.setUser(null);
	}
}