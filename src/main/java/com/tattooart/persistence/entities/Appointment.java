package com.tattooart.persistence.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "appointment")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "date")
	private LocalDate date;

	@Column(name = "time")
	private LocalTime time;

	@Column(name = "status")
	private Boolean status;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "tattoo_list_id", nullable = false)
	private TattooList tattooList;

	@ManyToOne
	@JoinColumn(name = "tattoo_ist_id", nullable = false)
	private Tattooist tattooIst;

	// Getters And Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public TattooList getTattooList() {
		return tattooList;
	}

	public void setTattooList(TattooList tattooList) {
		this.tattooList = tattooList;
	}

	public Tattooist getTattooIst() {
		return tattooIst;
	}

	public void setTattooIst(Tattooist tattooIst) {
		this.tattooIst = tattooIst;
	}
}