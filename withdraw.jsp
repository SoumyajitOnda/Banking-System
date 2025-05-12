<%@ page session="true" %>
<%
    String username = (String) session.getAttribute("username");
    if (username == null) {
        response.sendRedirect("customer_login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Withdraw Money</title>
    <link rel="stylesheet" href="../css/style.css">
    <style>
        .form-container {
            margin-top: 20px;
            text-align: center;
        }
        .form-container input {
            margin: 10px 0;
        }
        .form-container label {
            font-weight: bold;
        }
        .message {
            margin-top: 20px;
            text-align: center;
            color: green;
            font-weight: bold;
            font-size: 16px;
        }
    </style>
</head>
<body class="centered-bg">
    <div class="overlay"></div>
    <div class="login-box">
        <h2>Withdraw Funds</h2>

        <form action="../WithdrawServlet" method="post" class="form-container">
            <input type="hidden" name="username" value="<%= username %>">
            
            <label for="amount">Amount to Withdraw:</label><br>
            <input type="number" id="amount" name="amount" min="1" step="0.01" required><br>
            
            <input type="submit" class="btn" value="Withdraw">
        </form>
		<% if (request.getParameter("msg") != null) { %>
            <p class="message">
                <% if ("success".equals(request.getParameter("msg"))) { %>
                     Withdrawal successful!.
                <% } else if ("error".equals(request.getParameter("msg"))) { %>
                     Withdrawal failed! Please check your balance.
                <% } else if ("exception".equals(request.getParameter("msg"))) { %>
                     An exception occurred.
                <% } %>
            </p>
        <% } %>
        <a href="customer_dashboard.jsp" class="btn" style="margin-top: 15px;">Back to Dashboard</a>
    </div>
</body>
</html>
