package com.bank.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import com.bank.dao.CustomerDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ResetCustomerPasswordServlet")
public class ResetCustomerPasswordServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");

		if (!newPassword.equals(confirmPassword)) {
			request.setAttribute("error", "Passwords do not match!");
			request.getRequestDispatcher("pages/reset_customer_password.jsp").forward(request, response);
			return;
		}

		try {
			// Establish DB connection
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system", "root",
					"Soumyajit@123");

			// Use DAO
			CustomerDAO dao = new CustomerDAO(con);
			boolean success = dao.updateCustomerPassword(username, newPassword);

			if (success) {
				// Redirect to staff dashboard with message
				response.sendRedirect("pages/staff_dashboard.jsp?msg=Password+reset+successfully!");
			} else {
				request.setAttribute("error", "Failed to update password. Check username.");
				request.getRequestDispatcher("pages/reset_customer_password.jsp").forward(request, response);
			}

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
