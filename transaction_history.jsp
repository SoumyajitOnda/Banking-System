<%@ page import="java.sql.*,java.util.*" %>
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
    <title>Transaction History</title>
    <link rel="stylesheet" href="../css/style.css">
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
            color: black;
        }
        th, td {
            padding: 12px;
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
    <div class="login-box">
        <h2 style="color: black;">Your Transaction History</h2>
        <table>
            <tr>
                <th>Type</th>
                <th>Amount</th>
                <th>Date</th>
            </tr>
<%
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system", "root", "Soumyajit@123");
        String sql = "SELECT * FROM transactions WHERE username=? ORDER BY timestamp DESC";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
%>
            <tr>
                <td><%= rs.getString("type") %></td>
                <td><%= rs.getDouble("amount") %></td>
                <td><%= rs.getTimestamp("timestamp") %></td>
            </tr>
<%
        }
        rs.close();
        ps.close();
        con.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
        </table>
        <a href="customer_dashboard.jsp" class="btn" style="margin-top: 20px;">Back to Dashboard</a>
    </div>
</body>
</html>
