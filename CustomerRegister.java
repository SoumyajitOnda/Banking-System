package com.bank.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import com.bank.dao.CustomerDAO;
import com.bank.model.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CustomerRegister")
public class CustomerRegister extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String fullName = request.getParameter("fullName");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String dob = request.getParameter("dob");
		String gender = request.getParameter("gender");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirm_password");

		if (!password.equals(confirmPassword)) {
			request.setAttribute("error", "Passwords do not match!");
			request.getRequestDispatcher("pages/customer_register.jsp").forward(request, response);
			return;
		}

		Customer customer = new Customer(fullName, username, email, phone, address, dob, gender, password, "pending");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system", "root",
					"Soumyajit@123");

			CustomerDAO dao = new CustomerDAO(con);
			boolean status = dao.registerCustomer(customer);

			if (status) {
				response.sendRedirect("pages/customer_login.jsp");
			} else {
				request.setAttribute("error", "Registration failed. Try again.");
				request.getRequestDispatcher("pages/customer_register.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Something went wrong. Try again.");
			request.getRequestDispatcher("pages/customer_register.jsp").forward(request, response);
		}
	}
}
