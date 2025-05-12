<!-- WebContent/pages/customer_dashboard.jsp -->
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Dashboard</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <style>
        .dashboard-links {
            margin-top: 20px;
        }

        .dashboard-links a {
            display: block;
            margin: 10px 0;
            color: #fff;
            background-color: #007bff;
            padding: 10px;
            border-radius: 8px;
            text-align: center;
            text-decoration: none;
            transition: background-color 0.3s ease;
        }

        .dashboard-links a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body class="centered-bg">
    <div class="overlay"></div>
    <div class="login-box">
        <h2 class="login-title" style="color: black;">Welcome, <%= session.getAttribute("username") %>!</h2>
        <p style="color: black;">You are now logged in.</p>

        <div class="dashboard-links">
            <a href="withdraw.jsp">Withdraw</a>
            <a href="deposit.jsp">Deposit</a>
            <a href="check_balance.jsp">Check Balance</a>
            <a href="transaction_history.jsp">Transaction History</a>
            <a href="loan_request.jsp">Apply for loan</a>
            <a href="fixed_deposit.jsp">Open Fixed Deposit</a>
            <a href="fd_status.jsp">View Fixed Deposits</a>
            <a href="recurring_deposit.jsp">Open Recurring Deposit</a>
            <a href="rd_deposit.jsp">View Recurring Deposits</a>
        </div>

        <a href="../CustomerLogout" class="btn" style="margin-top: 20px;">Logout</a>
    </div>
</body>
</html>
