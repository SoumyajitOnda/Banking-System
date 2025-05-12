<%@ page import="java.sql.*, java.util.*" %>
<%@ page session="true" %>
<%
    String adminUser = (String) session.getAttribute("username");
    if (adminUser == null) {
        response.sendRedirect("admin_login.jsp");
        return;
    }

    String dbURL = "jdbc:mysql://localhost:3306/banking_system";
    String dbUser = "root";
    String dbPass = "Soumyajit@123";

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
%>
<!DOCTYPE html>
<html>
<head>
    <title>Approve Staff</title>
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
        <h2 style="color: black;">Pending Staff Approval</h2>

        <% if (request.getParameter("msg") != null) { %>
            <p class="message">
                <% if ("success".equals(request.getParameter("msg"))) { %>
                     Staff status updated successfully.
                <% } else if ("error".equals(request.getParameter("msg"))) { %>
                     Failed to update staff status.
                <% } else if ("exception".equals(request.getParameter("msg"))) { %>
                     An exception occurred while updating staff.
                <% } %>
            </p>
        <% } %>

        <table>
            <tr>
                <th>Full Name</th>
                <th>Username</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Branch</th>
                <th>Action</th>
            </tr>

            <%
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
                    String sql = "SELECT full_name, username, email, phone, branch FROM staff WHERE status = 'pending'";
                    ps = conn.prepareStatement(sql);
                    rs = ps.executeQuery();

                    while (rs.next()) {
            %>
                <tr>
                    <td><%= rs.getString("full_name") %></td>
                    <td><%= rs.getString("username") %></td>
                    <td><%= rs.getString("email") %></td>
                    <td><%= rs.getString("phone") %></td>
                    <td><%= rs.getString("branch") %></td>
                    <td>
                        <a href="../ApproveStaffServlet?action=approve&username=<%= rs.getString("username") %>" class="btn-small approve">Approve</a>
                        <a href="../ApproveStaffServlet?action=reject&username=<%= rs.getString("username") %>" class="btn-small reject">Reject</a>
                    </td>
                </tr>
            <%
                    }
                } catch (Exception e) {
                    out.println("<tr><td colspan='6'>Error fetching data</td></tr>");
                    e.printStackTrace();
                } finally {
                    if (rs != null) rs.close();
                    if (ps != null) ps.close();
                    if (conn != null) conn.close();
                }
            %>
        </table>
        <a href="admin_dashboard.jsp" class="btn" style="margin-top: 20px;">Back to Dashboard</a>
    </div>
</body>
</html>
