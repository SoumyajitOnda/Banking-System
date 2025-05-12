// src/com/bank/controller/Logout.java
package com.bank.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/AdminLogout")
public class AdminLogout extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Invalidate the session
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		// Redirect to login page or homepage
		response.sendRedirect("pages/admin_logout.jsp");
		// or wherever your login/home page is
	}
}
