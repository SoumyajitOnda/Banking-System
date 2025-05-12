package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bank.model.Staff;

public class StaffDAO {
	private Connection conn;

	public StaffDAO(Connection conn) {
		this.conn = conn;
	}

	public boolean registerStaff(Staff staff) {
		String query = "INSERT INTO staff (full_name, username, email, phone, branch, password, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, staff.getFullName());
			ps.setString(2, staff.getUsername());
			ps.setString(3, staff.getEmail());
			ps.setString(4, staff.getPhone());
			ps.setString(5, staff.getBranch());
			ps.setString(6, staff.getPassword());
			ps.setString(7, staff.getStatus());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Staff> getPendingStaff() {
		List<Staff> staffList = new ArrayList<>();
		String query = "SELECT * FROM staff WHERE status = 'pending'";
		try (PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Staff staff = new Staff();
				staff.setId(rs.getInt("id"));
				staff.setFullName(rs.getString("full_name"));
				staff.setUsername(rs.getString("username"));
				staff.setEmail(rs.getString("email"));
				staff.setPhone(rs.getString("phone"));
				staff.setBranch(rs.getString("branch"));
				staff.setPassword(rs.getString("password"));
				staff.setCreatedAt(rs.getTimestamp("created_at"));
				staff.setStatus(rs.getString("status"));
				staffList.add(staff);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return staffList;
	}

	public void approveStaffByIds(String[] ids) {
		String query = "UPDATE staff SET status = 'approved' WHERE id = ?";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			for (String id : ids) {
				ps.setInt(1, Integer.parseInt(id));
				ps.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
