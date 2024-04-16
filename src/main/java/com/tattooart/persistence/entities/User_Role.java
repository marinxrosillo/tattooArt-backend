package com.tattooart.persistence.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_role")
public class User_Role {

	@Id
	@Column(name = "id_user_role")
	private int idUserRole;
	@Id
	private String role;
	private Date granted_Date;

	@ManyToOne
	@JoinColumn(name = "id_user_role", insertable = false, updatable = false)
	private User user;

	// Getters And Setters
	public int getIdUserRole() {
		return idUserRole;
	}

	public void setIdUserRole(int idUserRole) {
		this.idUserRole = idUserRole;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getGranted_Date() {
		return granted_Date;
	}

	public void setGranted_Date(Date granted_Date) {
		this.granted_Date = granted_Date;
	}
}
