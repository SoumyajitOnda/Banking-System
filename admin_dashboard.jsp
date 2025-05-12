<%@ page session="true" %>
<%
    String adminUser = (String) session.getAttribute("username");
    if (adminUser == null) {
        response.sendRedirect("admin_login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Manager Dashboard</title>
    <link rel="stylesheet" href="../css/style.css">
    
</head>
<body class="centered-bg">
	<div class="overlay"></div>
    <div class="login-box">
        <h2 style="color: black;">Welcome, Manager <%= adminUser %></h2>
        <p style="color: black;">Login successful!</p>
        <div class="button-group">
        	<a href="admin_loan_requests.jsp" class="btn">Approve loans</a>
            <a href="approve_staff.jsp" class="btn">Approve staffs</a>
        	<a href="approve_customer.jsp" class="btn">Approve customers</a>
            <a href="view_staffs.jsp" class="btn">View all staffs</a>
            <a href="view_customers.jsp" class="btn">View all customers</a>
        </div>
        <div class="header-buttons">
	    	<a href="../AdminLogout" class="btn">Logout</a>
		</div>
    </div>
</body>
</html>
