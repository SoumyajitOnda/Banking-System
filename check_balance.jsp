<%@ page session="true" %>
<%
    String username = (String) session.getAttribute("username");
    if (username == null) {
        response.sendRedirect("customer_login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Check Balance</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body class="centered-bg">
    <div class="overlay"></div>
    <div class="login-box">
        <h2 class="login-title" style="color: black;">Check Your Balance</h2>

        <form action="../CheckBalance" method="get">
            <input type="submit" value="View Balance" class="btn" />
        </form>

        <a href="customer_dashboard.jsp" class="btn" style="margin-top: 20px;">Back to Dashboard</a>
    </div>
</body>
</html>
