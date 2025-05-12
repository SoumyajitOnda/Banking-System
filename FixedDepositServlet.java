package com.bank.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.dao.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/FixedDepositServlet")
public class FixedDepositServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get the username from session
		String username = (String) request.getSession().getAttribute("username");

		// If username is not found in session, redirect to login page
		if (username == null) {
			response.sendRedirect("customer_login.jsp");
			return;
		}

		// Get the form input parameters
		String amountStr = request.getParameter("amount");
		String durationStr = request.getParameter("duration");

		// Validate input parameters
		if (amountStr == null || durationStr == null || amountStr.isEmpty() || durationStr.isEmpty()) {
			response.sendRedirect("pages/fixed_deposit.jsp?msg=emptyfields");
			return;
		}

		double amount = 0;
		int duration = 0;

		// Try to parse the amount and duration, handle invalid inputs
		try {
			amount = Double.parseDouble(amountStr);
			duration = Integer.parseInt(durationStr);
		} catch (NumberFormatException e) {
			response.sendRedirect("pages/fixed_deposit.jsp?msg=invalidinput");
			return;
		}

		// Validate that the amount and duration are positive
		if (amount <= 0 || duration <= 0) {
			response.sendRedirect("pages/fixed_deposit.jsp?msg=invalidinput");
			return;
		}

		// Check the customer's account status
		try (Connection con = DBConnection.getConnection()) {
			// Query to check the status of the customer
			String customerStatusQuery = "SELECT status FROM customer WHERE username = ?";
			try (PreparedStatement ps = con.prepareStatement(customerStatusQuery)) {
				ps.setString(1, username);
				ResultSet rs = ps.executeQuery();

				// If the customer doesn't exist or has an invalid status
				if (!rs.next()) {
					response.sendRedirect("pages/fixed_deposit.jsp?msg=customer_not_found");
					return;
				}

				String customerStatus = rs.getString("status");
				if (!"approved".equals(customerStatus)) {
					response.sendRedirect("pages/fixed_deposit.jsp?msg=customer_not_approved");
					return;
				}

				// Proceed with fixed deposit creation if the customer is approved
				String sql = "INSERT INTO fd_accounts (customer_username, amount, interest_rate, duration_months, start_date, maturity_date, status) "
						+ "VALUES (?, ?, 5.0, ?, CURDATE(), DATE_ADD(CURDATE(), INTERVAL ? MONTH), 'active')";

				try (PreparedStatement ps2 = con.prepareStatement(sql)) {
					ps2.setString(1, username);
					ps2.setDouble(2, amount);
					ps2.setInt(3, duration);
					ps2.setInt(4, duration);

					// Execute the query and check if the insertion was successful
					int rowsAffected = ps2.executeUpdate();
					if (rowsAffected > 0) {
						response.sendRedirect("pages/fixed_deposit.jsp?msg=success");
					} else {
						response.sendRedirect("pages/fixed_deposit.jsp?msg=failed");
					}
				} catch (SQLException e) {
					// Log the SQL error and send the response with a detailed error message
					e.printStackTrace();
					response.sendRedirect("pages/fixed_deposit.jsp?msg=sqlerror");
				}
			} catch (SQLException e) {
				// Log the database connection error and send a response with a detailed error
				// message
				e.printStackTrace();
				response.sendRedirect("pages/fixed_deposit.jsp?msg=sqlerror");
			}
		} catch (SQLException e) {
			// Log the database connection error and send a response with a detailed error
			// message
			e.printStackTrace();
			response.sendRedirect("pages/fixed_deposit.jsp?msg=sqlerror");
		}
	}
}
