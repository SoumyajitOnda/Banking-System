package com.bank.model;

import java.sql.Timestamp;

public class Staff {
	private int id;
	private String fullName;
	private String username;
	private String email;
	private String phone;
	private String branch;
	private String password;
	private Timestamp createdAt;
	private String status;

	// Empty constructor
	public Staff() {
	}

	// Constructor for registration
	public Staff(String fullName, String username, String email, String phone, String branch, String password,
			String status) {
		this.fullName = fullName;
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.branch = branch;
		this.password = password;
		this.status = status;
	}

	// Getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
