package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bank.model.RecurringDeposit;

public class RecurringDepositDAO {

	// Method to fetch all Recurring Deposits for a customer
	public static List<RecurringDeposit> getAllRdsForCustomer(String username) {
		List<RecurringDeposit> rdList = new ArrayList<>();
		try (Connection con = DBConnection.getConnection()) {
			String sql = "SELECT * FROM rd_accounts WHERE customer_username = ? AND status = 'active'";
			try (PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setString(1, username);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					RecurringDeposit rd = new RecurringDeposit();
					rd.setId(rs.getInt("id"));
					rd.setCustomerUsername(rs.getString("customer_username"));
					rd.setMonthlyDeposit(rs.getDouble("monthly_deposit"));
					rd.setInterestRate(rs.getDouble("interest_rate"));
					rd.setDurationMonths(rs.getInt("duration_months"));
					rd.setStartDate(rs.getDate("start_date"));
					rd.setMaturityDate(rs.getDate("maturity_date"));
					rd.setStatus(rs.getString("status"));
					rdList.add(rd);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rdList;
	}

	// Method to insert a new Recurring Deposit
	public static boolean createRecurringDeposit(RecurringDeposit rd) {
		try (Connection con = DBConnection.getConnection()) {
			String sql = "INSERT INTO rd_accounts (customer_username, monthly_deposit, interest_rate, duration_months, start_date, maturity_date) VALUES (?, ?, ?, ?, ?, ?)";
			try (PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setString(1, rd.getCustomerUsername());
				ps.setDouble(2, rd.getMonthlyDeposit());
				ps.setDouble(3, rd.getInterestRate());
				ps.setInt(4, rd.getDurationMonths());
				ps.setDate(5, new java.sql.Date(rd.getStartDate().getTime()));
				ps.setDate(6, new java.sql.Date(rd.getMaturityDate().getTime()));
				int rowsAffected = ps.executeUpdate();
				return rowsAffected > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
