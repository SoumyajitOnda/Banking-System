package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bank.model.Customer;

public class CustomerDAO {
	private Connection con;

	// Constructor that accepts a Connection
	public CustomerDAO(Connection con) {
		this.con = con;
	}

	public boolean registerCustomer(Customer c) {
		boolean success = false;
		try {
			String sql = "INSERT INTO customer(full_name, username, email, phone, address, dob, gender, password, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, c.getFullName());
			ps.setString(2, c.getUsername());
			ps.setString(3, c.getEmail());
			ps.setString(4, c.getPhone());
			ps.setString(5, c.getAddress());
			ps.setString(6, c.getDob());
			ps.setString(7, c.getGender());
			ps.setString(8, c.getPassword());
			ps.setString(9, c.getStatus());
			int rows = ps.executeUpdate();
			if (rows > 0)
				success = true;

			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}

	public boolean updateCustomerPassword(String username, String newPassword) {
		boolean success = false;
		try {
			String query = "UPDATE customer SET password = ? WHERE username = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, newPassword);
			ps.setString(2, username);

			int rowsUpdated = ps.executeUpdate();
			if (rowsUpdated > 0) {
				success = true;
			}

			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}

	public boolean withdrawAmount(String username, double amount) {
		boolean success = false;
		try {
			con.setAutoCommit(false);

			String checkSql = "SELECT balance FROM customer WHERE username = ?";
			PreparedStatement checkStmt = con.prepareStatement(checkSql);
			checkStmt.setString(1, username);
			ResultSet rs = checkStmt.executeQuery();

			if (rs.next()) {
				double currentBalance = rs.getDouble("balance");
				if (currentBalance >= amount) {
					String updateSql = "UPDATE customer SET balance = balance - ? WHERE username = ?";
					PreparedStatement updateStmt = con.prepareStatement(updateSql);
					updateStmt.setDouble(1, amount);
					updateStmt.setString(2, username);
					int rows = updateStmt.executeUpdate();
					if (rows > 0) {
						success = true;
						con.commit();
					}
					updateStmt.close();
				}
			}
			rs.close();
			checkStmt.close();
			con.setAutoCommit(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}

	public boolean depositAmount(String username, double amount) {
		boolean success = false;
		try {
			con.setAutoCommit(false);

			String updateSql = "UPDATE customer SET balance = balance + ? WHERE username = ?";
			PreparedStatement updateStmt = con.prepareStatement(updateSql);
			updateStmt.setDouble(1, amount);
			updateStmt.setString(2, username);

			int rows = updateStmt.executeUpdate();
			if (rows > 0) {
				success = true;
				con.commit();
			}

			updateStmt.close();
			con.setAutoCommit(true);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback(); // In case something fails
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return success;
	}

	public double getBalance(String username) {
		double balance = -1.0;
		try {
			String sql = "SELECT balance FROM customer WHERE username = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				balance = rs.getDouble("balance");
			}

			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return balance;
	}
}
