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
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoanRequestServlet")
public class LoanRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String DB_URL = "jdbc:mysql://localhost:3306/banking_system";
	private static final String DB_USER = "root";
	private static final String DB_PASS = "Soumyajit@123"; // ← Your DB password

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String username = (String) request.getSession().getAttribute("username");

		if (username == null) {
			response.sendRedirect("customer_login.jsp");
			return;
		}

		double amount = Double.parseDouble(request.getParameter("amount"));
		String purpose = request.getParameter("purpose");
		int tenure = Integer.parseInt(request.getParameter("tenure"));
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

			String sql = "INSERT INTO loan_requests (username, amount, purpose, tenure_months, status) VALUES (?, ?, ?, ?, 'pending')";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setDouble(2, amount);
			ps.setString(3, purpose);
			ps.setInt(4, tenure);

			int rows = ps.executeUpdate();

			if (rows > 0) {
				response.sendRedirect("pages/loan_request.jsp?message=Loan+request+submitted+successfully");
			} else {
				response.sendRedirect("pages/loan_request.jsp?message=Failed+to+submit+loan+request");
			}

			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("pages/error.jsp");
		}
	}
}
