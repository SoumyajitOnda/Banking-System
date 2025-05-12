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
    <title>Loan Request</title>
    <link rel="stylesheet" href="../css/style.css">
    <style>
        .form-container {
            margin-top: 20px;
            text-align: center;
        }
        .form-container input, .form-container textarea {
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
        <h2>Apply for Loan</h2>

        <form action="../LoanRequestServlet" method="post" class="form-container">
            <input type="hidden" name="username" value="<%= username %>">

            <label for="amount">Loan Amount:</label><br>
            <input type="number" id="amount" name="amount" min="1000" step="100" required><br>

            <label for="purpose">Purpose:</label><br>
            <textarea id="purpose" name="purpose" rows="3" cols="30" required></textarea><br>

            <label for="tenure">Tenure (months):</label><br>
            <input type="number" id="tenure" name="tenure" min="1" max="120" required><br>

            <input type="submit" class="btn" value="Submit Loan Request">
        </form>

        <% if (request.getParameter("message") != null) { %>
            <p class="message"><%= request.getParameter("message") %></p>
        <% } %>

        <a href="customer_dashboard.jsp" class="btn" style="margin-top: 15px;">Back to Dashboard</a>
    </div>
</body>
</html>
