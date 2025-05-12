<%@ page session="true" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bank.model.RecurringDeposit" %>
<%@ page import="com.bank.dao.RecurringDepositDAO" %>
<%
    String username = (String) session.getAttribute("username");
    if (username == null) {
        response.sendRedirect("customer_login.jsp");
        return;
    }

    // Get all Recurring Deposits for this customer
    List<RecurringDeposit> rdList = RecurringDepositDAO.getAllRdsForCustomer(username);
%>

<!DOCTYPE html>
<html>
<head>
    <title>Customer Dashboard</title>
    <link rel="stylesheet" href="../css/style.css">
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: white;
            color: black;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ccc;
        }
        th {
            background-color: #007bff;
            color: white;
        }
    </style>
</head>
<body class="centered-bg">
	<div class="overlay"></div>
	<div class="login-box" style="width: 90%; max-width: 1000px;">
        <h3 >Your Recurring Deposits</h3>
        <% if (rdList.isEmpty()) { %>
            <p>No Recurring Deposit accounts found.</p>
        <% } else { %>
            <table>
                <thead>
                    <tr>
                        <th>Monthly Deposit</th>
                        <th>Interest Rate</th>
                        <th>Duration (Months)</th>
                        <th>Start Date</th>
                        <th>Maturity Date</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (RecurringDeposit rd : rdList) { %>
                        <tr>
                            <td><%= rd.getMonthlyDeposit() %></td>
                            <td><%= rd.getInterestRate() %>%</td>
                            <td><%= rd.getDurationMonths() %></td>
                            <td><%= rd.getStartDate() %></td>
                            <td><%= rd.getMaturityDate() %></td>
                            <td><%= rd.getStatus() %></td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        <% } %>
        <a href="customer_dashboard.jsp" class="btn" style="margin-top: 20px;">Back to Dashboard</a>
    </div>
</body>
</html>
