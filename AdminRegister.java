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

@WebServlet("/AdminRegister")
public class AdminRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Update these with your DB info
	private static final String DB_URL = "jdbc:mysql://localhost:3306/banking_system";
	private static final String DB_USER = "root";
	private static final String DB_PASS = "Soumyajit@123";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirm_password");

		if (!password.equals(confirmPassword)) {
			request.setAttribute("error", "Passwords do not match!");
			request.getRequestDispatcher("pages/admin_register.jsp").forward(request, response);
			return;
		}

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

			String sql = "INSERT INTO admin (username, password) VALUES (?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password); // In production, hash this!

			int rowsInserted = stmt.executeUpdate();

			if (rowsInserted > 0) {
				response.sendRedirect("pages/admin_login.jsp");
			} else {
				request.setAttribute("error", "Something went wrong. Try again!");
				request.getRequestDispatcher("pages/admin_register.jsp").forward(request, response);
			}

			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Server error: " + e.getMessage());
			request.getRequestDispatcher("pages/admin_register.jsp").forward(request, response);
		}
	}
}
