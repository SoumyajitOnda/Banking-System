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

@WebServlet("/ApproveStaffServlet")
public class ApproveStaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String DB_URL = "jdbc:mysql://localhost:3306/banking_system";
	private static final String DB_USER = "root";
	private static final String DB_PASS = "Soumyajit@123";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action"); // "approve" or "reject"
		String username = request.getParameter("username");

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);) {
			String newStatus = action.equals("approve") ? "approved" : "rejected";
			String sql = "UPDATE staff SET status = ? WHERE username = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, newStatus);
			ps.setString(2, username);

			int updated = ps.executeUpdate();

			if (updated > 0) {
				response.sendRedirect("pages/approve_staff.jsp?msg=success");
			} else {
				response.sendRedirect("pages/approve_staff.jsp?msg=error");
			}

			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("pages/approve_staff.jsp?msg=exception");
		}
	}
}
