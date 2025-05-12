<%@ page session="true" %>
<%
    String staffUser = (String) session.getAttribute("username");
    if (staffUser == null) {
        response.sendRedirect("staff_login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Staff Dashboard</title>
    <link rel="stylesheet" href="../css/style.css">
    <style>
        .dashboard-box {
            max-width: 500px;
            margin: auto;
            padding: 30px;
            background-color: white;
            border-radius: 15px;
            box-shadow: 0 0 15px rgba(0,0,0,0.3);
            text-align: center;
        }
        ul {
            text-align: left;
            margin-top: 15px;
        }
        .logout-link {
            display: inline-block;
            margin-top: 20px;
            padding: 8px 16px;
            background-color: #e60000;
            color: white;
            text-decoration: none;
            border-radius: 8px;
        }
        .logout-link:hover {
            background-color: #cc0000;
        }
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
    <div class="dashboard-box">
        <h2 style="color: black;">Welcome, Staff <%= staffUser %></h2>
        <p style="color: black;">Login successful!</p>
        <div class="dashboard-links">
            <a href="edit_customer.jsp" class="btn">update customer details</a>
            <a href="reset_customer_password.jsp" class="btn">Reset customer password</a>
        </div>
		<a href="../StaffLogout" class="btn">Logout</a>
    </div>
</body>
</html>
