<%@ page import="java.sql.*" %>
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
    <title>FD Status</title>
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
        <h2 style="color: black;">Your Fixed Deposits</h2>
        <%
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system", "root", "Soumyajit@123");

                String sql = "SELECT * FROM fd_accounts WHERE customer_username = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();
        %>
        <table>
            <tr>
                <th>Amount (Rs.)</th>
                <th>Interest Rate (%)</th>
                <th>Duration (Months)</th>
                <th>Start Date</th>
                <th>Maturity Date</th>
                <th>Status</th>
            </tr>
        <%
            while (rs.next()) {
        %>
            <tr>
                <td><%= rs.getDouble("amount") %></td>
                <td><%= rs.getDouble("interest_rate") %></td>
                <td><%= rs.getInt("duration_months") %></td>
                <td><%= rs.getDate("start_date") %></td>
                <td><%= rs.getDate("maturity_date") %></td>
                <td><%= rs.getString("status") %></td>
            </tr>
        <%
            }
                rs.close();
                ps.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
        %>
            <p style="color: red;">Error fetching FD records.</p>
        <%
            }
        %>
        </table>

        <a href="customer_dashboard.jsp" class="btn" style="margin-top: 20px;">Back to Dashboard</a>
    </div>
</body>
</html>
