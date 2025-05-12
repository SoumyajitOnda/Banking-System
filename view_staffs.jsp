<%@ page import="java.sql.*" %>
<%@ page session="true" %>
<%
    String adminUser = (String) session.getAttribute("username");
    if (adminUser == null) {
        response.sendRedirect("admin_login.jsp");
        return;
    }

    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
%>
<!DOCTYPE html>
<html>
<head>
    <title>All Staffs</title>
    <link rel="stylesheet" href="../css/style.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f4f6f7;
            padding: 30px;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        table {
            width: 95%;
            margin: auto;
            border-collapse: collapse;
            background: #fff;
            box-shadow: 0 0 10px #ccc;
        }
        th, td {
            padding: 12px 15px;
            border: 1px solid #ddd;
            text-align: center;
        }
        th {
            background-color: #2980b9;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        .back-btn {
            display: block;
            width: 200px;
            margin: 30px auto;
            text-align: center;
            padding: 10px;
            background-color: #2980b9;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        .back-btn:hover {
            background-color: #1c5980;
        }
    </style>
</head>
<body>
    <h2>Registered Staff Members</h2>
    <table>
        <tr>
            <th>ID</th>
            <th>Full Name</th>
            <th>Username</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Branch</th>
            <th>Status</th>
            <th>Registered On</th>
        </tr>
        <%
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system", "root", "Soumyajit@123");
                stmt = con.createStatement();
                rs = stmt.executeQuery("SELECT * FROM staff");

                while (rs.next()) {
        %>
        <tr>
            <td><%= rs.getInt("id") %></td>
            <td><%= rs.getString("full_name") %></td>
            <td><%= rs.getString("username") %></td>
            <td><%= rs.getString("email") %></td>
            <td><%= rs.getString("phone") %></td>
            <td><%= rs.getString("branch") %></td>
            <td><%= rs.getString("status") %></td>
            <td><%= rs.getTimestamp("created_at") %></td>
        </tr>
        <%
                }
            } catch (Exception e) {
                out.println("<tr><td colspan='8' style='color:red;'>Error: " + e.getMessage() + "</td></tr>");
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (stmt != null) stmt.close();
                    if (con != null) con.close();
                } catch (SQLException se) {
                    out.println("<tr><td colspan='8' style='color:red;'>Closing error: " + se.getMessage() + "</td></tr>");
                }
            }
        %>
    </table>

    <a href="admin_dashboard.jsp" class="back-btn">Back to Dashboard</a>
</body>
</html>
