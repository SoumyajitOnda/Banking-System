package com.bank.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import com.bank.dao.StaffDAO;
import com.bank.model.Staff;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/StaffRegister")
public class StaffRegister extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String fullName = request.getParameter("fullName");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String branch = request.getParameter("branch");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirm_password");

		if (!password.equals(confirmPassword)) {
			request.setAttribute("error", "Passwords do not match!");
			request.getRequestDispatcher("pages/staff_register.jsp").forward(request, response);
			return;
		}

		Staff staff = new Staff(fullName, username, email, phone, branch, password, "pending");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system", "root",
					"Soumyajit@123");

			StaffDAO dao = new StaffDAO(conn);
			boolean status = dao.registerStaff(staff);

			if (status) {
				response.sendRedirect("pages/staff_login.jsp");
			} else {
				request.setAttribute("error", "Registration failed. Try again.");
				request.getRequestDispatcher("pages/staff_register.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Database error.");
			request.getRequestDispatcher("pages/staff_register.jsp").forward(request, response);
		}
	}
}
