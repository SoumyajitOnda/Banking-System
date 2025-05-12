package com.bank.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UpdateCustomer")
public class UpdateCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String DB_URL = "jdbc:mysql://localhost:3306/banking_system";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "Soumyajit@123";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String fullName = request.getParameter("full_name");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String dob = request.getParameter("dob");
		String gender = request.getParameter("gender");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			String sql = "UPDATE customer SET full_name=?, username=?, email=?, phone=?, address=?, dob=?, gender=? WHERE id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, fullName);
			ps.setString(2, username);
			ps.setString(3, email);
			ps.setString(4, phone);
			ps.setString(5, address);
			ps.setString(6, dob);
			ps.setString(7, gender);
			ps.setInt(8, id);

			int rows = ps.executeUpdate();
			ps.close();
			con.close();

			if (rows > 0) {
				response.sendRedirect("pages/staff_dashboard.jsp?msg=Customer+updated+successfully");
			} else {
				response.sendRedirect("pages/edit_customer.jsp?error=Update+failed");
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("pages/edit_customer.jsp?error=Exception+" + e.getMessage());
		}
	}
}
