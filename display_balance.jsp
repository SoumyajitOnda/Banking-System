<%@ page session="true" %>
<%
    Double balance = (Double) session.getAttribute("balance");
    String username = (String) session.getAttribute("username");

    if (balance == null) {
        response.sendRedirect("check_balance.jsp"); // fallback if balance not found
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Your Balance</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <style>
        .balance-amount {
            font-size: 28px;
            color: green;
            font-weight: bold;
            margin: 10px 0;
        }
    </style>
</head>
<body class="centered-bg">
    <div class="overlay"></div>
    <div class="login-box">
        <h2 class="login-title" style="color: black;">Hello, <%= username %></h2>
        <p style="color: black; font-size: 18px;">Your current balance is:</p>
        <div class="balance-amount">
            Rs. <%= String.format("%.2f", balance) %>
        </div>
        <a href="customer_dashboard.jsp" class="btn" style="margin-top: 20px;">Back to Dashboard</a>
    </div>
</body>
</html>
