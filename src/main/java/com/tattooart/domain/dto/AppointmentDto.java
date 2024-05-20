package com.tattooart.domain.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.tattooart.persistence.entities.TattooList;
import com.tattooart.persistence.entities.Tattooist;

public class AppointmentDto {

		private int id;

		private LocalDate date;

		private LocalTime time;

		private Boolean status;

		private String user;

		private TattooList tattooList;
	
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

		public String getUser() {
			return user;
		}

		public void setUser(String user) {
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
