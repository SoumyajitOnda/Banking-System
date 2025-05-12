package com.bank.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/CustomerLogin")
public class CustomerLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String DB_URL = "jdbc:mysql://localhost:3306/banking_system";
	private static final String DB_USER = "root";
	private static final String DB_PASS = "Soumyajit@123"; // Use your actual password

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			String sql = "SELECT * FROM customer WHERE username=? AND password=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				response.sendRedirect("pages/customer_dashboard.jsp");
			} else {
				response.sendRedirect("pages/error.jsp");
			}

			rs.close();
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("pages/error.jsp");
		}
	}
}
