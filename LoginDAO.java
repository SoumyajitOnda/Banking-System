// src/com/bank/dao/LoginDAO.java
package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {
	public boolean validate(String username, String password, String role) {
		String query = "SELECT * FROM users WHERE username=? AND password=? AND role=?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, role);

			ResultSet rs = ps.executeQuery();
			return rs.next(); // true if match found
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
