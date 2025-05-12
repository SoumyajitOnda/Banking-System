<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Approve Customers</title>
    <link rel="stylesheet" href="../css/style.css">
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            color: black;
        }
        th, td {
            padding: 12px;
            border: 1px solid #ddd;
        }
        th {
            background-color: #007bff;
            color: white;
        }
        .btn-small {
            padding: 5px 10px;
            margin: 2px;
            border: none;
            color: white;
            border-radius: 4px;
            cursor: pointer;
        }
        .approve {
            background-color: #28a745;
        }
        .reject {
            background-color: #dc3545;
        }
        .message {
            color: green;
            margin-top: 10px;
        }
    </style>
</head>
<body class="centered-bg">
    <div class="overlay"></div>
    <div class="login-box" style="width: 90%; max-width: 1000px;">
        <h2 style="color: black;">Approve Customer Registrations</h2>
        
        <%
            String msg = request.getParameter("msg");
            if ("success".equals(msg)) {
        %>
            <p style="color: green;">Status updated successfully!</p>
        <% } else if ("error".equals(msg)) { %>
            <p style="color: red;">Failed to update status.</p>
        <% } else if ("exception".equals(msg)) { %>
            <p style="color: red;">Something went wrong!</p>
        <% } %>

        <%
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system", "root", "Soumyajit@123");

                String sql = "SELECT * FROM customer WHERE status = 'pending'";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        %>
            <table border="1" style="width: 100%; margin-top: 20px; background-color: white; color: black;">
                <tr>
                    <th>ID</th>
                    <th>Username</th>
                    <th>Full Name</th>
                    <th>Email</th>
                    <th>Action</th>
                </tr>
        <%
                while (rs.next()) {
        %>
                <tr>
                    <td><%= rs.getInt("id") %></td>
                    <td><%= rs.getString("username") %></td>
                    <td><%= rs.getString("full_name") %></td>
                    <td><%= rs.getString("email") %></td>
                    <td>
                        <a href="../ApproveCustomerServlet?action=approve&username=<%= rs.getString("username") %>" class="btn-small approve">Approve</a>
                        <a href="../ApproveCustomerServlet?action=reject&username=<%= rs.getString("username") %>" class="btn-small reject">Reject</a>
                    </td>
                </tr>
        <%
                }
                rs.close();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        %>
            </table>
        <br/>
        <a href="admin_dashboard.jsp" class="btn">Back to Dashboard</a>
    </div>
</body>
</html>
