package com.bank.model;

public class Admin {
	private String fullName, username, email, phone, branch, password;

	public Admin(String fullName, String username, String email, String phone, String branch, String password) {
		this.fullName = fullName;
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.branch = branch;
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public String getBranch() {
		return branch;
	}

	public String getPassword() {
		return password;
	}
}
