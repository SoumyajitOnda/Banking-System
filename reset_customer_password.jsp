<%@ page session="true" %>
<%
    String staffUser = (String) session.getAttribute("username");
    if (staffUser == null) {
        response.sendRedirect("staff_login.jsp");
        return;
    }

    String error = (String) request.getAttribute("error");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Reset Customer Password</title>
    <link rel="stylesheet" href="../css/style.css">
    <style>
        .reset-password-box {
            max-width: 500px;
            margin: auto;
            padding: 30px;
            background-color: white;
            border-radius: 15px;
            box-shadow: 0 0 15px rgba(0,0,0,0.3);
            text-align: center;
        }
        .reset-password-box input {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        .reset-btn {
            padding: 10px 20px;
            background-color: #2980b9;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .reset-btn:hover {
            background-color: #1c5980;
        }
        .error {
            color: red;
            font-weight: bold;
            margin-bottom: 20px;
        }
    </style>
</head>
<body class="centered-bg">
    <div class="overlay"></div>
    <div class="reset-password-box">
        <h2>Reset Customer Password</h2>

        <% if (error != null) { %>
            <div class="error"><%= error %></div>
        <% } %>

        <form action="../ResetCustomerPasswordServlet" method="post">
            <label for="username">Customer Username:</label>
            <input type="text" name="username" required>

            <label for="newPassword">New Password:</label>
            <input type="password" name="newPassword" required>

            <label for="confirmPassword">Confirm Password:</label>
            <input type="password" name="confirmPassword" required>

            <input type="submit" value="Reset Password" class="reset-btn">
        </form>
    </div>
    
</body>
</html>
