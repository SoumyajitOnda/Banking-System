package com.bank.controller;

import java.io.IOException;

import com.bank.dao.RecurringDepositDAO;
import com.bank.model.RecurringDeposit;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RecurringDepositServlet")
public class RecurringDepositServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		double monthlyDeposit = Double.parseDouble(request.getParameter("monthly_deposit"));
		double interestRate = Double.parseDouble(request.getParameter("interest_rate"));
		int duration = Integer.parseInt(request.getParameter("duration"));

		// Calculate maturity date based on duration
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.add(java.util.Calendar.MONTH, duration);
		java.util.Date maturityDate = cal.getTime();

		// Create RecurringDeposit object
		RecurringDeposit rd = new RecurringDeposit();
		rd.setCustomerUsername(username);
		rd.setMonthlyDeposit(monthlyDeposit);
		rd.setInterestRate(interestRate);
		rd.setDurationMonths(duration);
		rd.setStartDate(new java.util.Date());
		rd.setMaturityDate(maturityDate);

		// Insert the RD into the database
		boolean success = RecurringDepositDAO.createRecurringDeposit(rd);

		if (success) {
			response.sendRedirect("pages/recurring_deposit.jsp?msg=success");
		} else {
			response.sendRedirect("pages/recurring_deposit.jsp?msg=error");
		}
	}
}
