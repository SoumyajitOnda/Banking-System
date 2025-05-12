package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.bank.model.Admin;

public class AdminDAO {
	private Connection conn;

	public AdminDAO(Connection conn) {
		this.conn = conn;
	}

	public boolean registerAdmin(Admin admin) {
		boolean success = false;
		try {
			String sql = "INSERT INTO admin(full_name, username, email, phone, branch, password) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, admin.getFullName());
			ps.setString(2, admin.getUsername());
			ps.setString(3, admin.getEmail());
			ps.setString(4, admin.getPhone());
			ps.setString(5, admin.getBranch());
			ps.setString(6, admin.getPassword());
			int rows = ps.executeUpdate();
			if (rows > 0)
				success = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}

	// Approve staff registration
	public boolean approveStaff(String username) {
		boolean success = false;
		try {
			String sql = "UPDATE staff SET status = 'approved' WHERE username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			int rows = ps.executeUpdate();
			if (rows > 0)
				success = true;
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}

	// Approve customer registration (if customer has a status column)
	public boolean approveCustomer(String username) {
		boolean success = false;
		try {
			String sql = "UPDATE customer SET status = 'approved' WHERE username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			int rows = ps.executeUpdate();
			if (rows > 0)
				success = true;
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}

}
