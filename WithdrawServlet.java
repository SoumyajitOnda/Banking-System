package com.bank.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.bank.dao.CustomerDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/WithdrawServlet")
public class WithdrawServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get the username and withdrawal amount from the request
		String username = request.getParameter("username");
		double amount = Double.parseDouble(request.getParameter("amount"));

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system", "root",
					"Soumyajit@123");

			CustomerDAO dao = new CustomerDAO(con);
			boolean result = dao.withdrawAmount(username, amount);
			String sql = "INSERT INTO transactions (username, type, amount) VALUES (?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, "withdraw");
			ps.setDouble(3, amount);
			ps.executeUpdate();

			if (result) {
				response.sendRedirect("pages/withdraw.jsp?msg=success");
			} else {
				response.sendRedirect("pages/withdraw.jsp?msg=error");
			}

			// Forward back to withdraw.jsp (NOT redirect)
			// RequestDispatcher dispatcher =
			// request.getRequestDispatcher("pages/withdraw.jsp");
			// dispatcher.forward(request, response);

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("pages/withdraw.jsp?msg=exception");
			// request.setAttribute("error", "Something went wrong. Please try again
			// later.");
			// RequestDispatcher dispatcher =
			// request.getRequestDispatcher("pages/withdraw.jsp");
			// dispatcher.forward(request, response);
		}
	}
}
