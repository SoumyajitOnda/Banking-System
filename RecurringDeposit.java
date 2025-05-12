package com.bank.model;

import java.util.Date;

public class RecurringDeposit {
	private int id;
	private String customerUsername;
	private double monthlyDeposit;
	private double interestRate;
	private int durationMonths;
	private Date startDate;
	private Date maturityDate;
	private String status;

	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomerUsername() {
		return customerUsername;
	}

	public void setCustomerUsername(String customerUsername) {
		this.customerUsername = customerUsername;
	}

	public double getMonthlyDeposit() {
		return monthlyDeposit;
	}

	public void setMonthlyDeposit(double monthlyDeposit) {
		this.monthlyDeposit = monthlyDeposit;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public int getDurationMonths() {
		return durationMonths;
	}

	public void setDurationMonths(int durationMonths) {
		this.durationMonths = durationMonths;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
