package com.bank.controller;

import java.io.IOException;
import java.sql.Connection;

import com.bank.dao.CustomerDAO;
import com.bank.dao.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/CheckBalance")
public class CheckBalanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");

		if (username == null) {
			response.sendRedirect("customer_login.jsp");
			return;
		}

		try {
			Connection con = DBConnection.getConnection();
			CustomerDAO dao = new CustomerDAO(con);

			double balance = dao.getBalance(username);
			session.setAttribute("balance", balance);
			response.sendRedirect("pages/display_balance.jsp");

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("pages/error.jsp");
		}
	}
}
