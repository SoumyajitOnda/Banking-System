<%@ page import="java.sql.*" %>
<%@ page session="true" %>
<%
    String staffUser = (String) session.getAttribute("username");
    if (staffUser == null) {
        response.sendRedirect("staff_login.jsp");
        return;
    }

    String idParam = request.getParameter("id");
    int custId = 0;
    String fullName = "", username = "", email = "", phone = "", address = "", gender = "";
    Date dob = null;

    boolean showForm = false;

    if (idParam != null && !idParam.isEmpty()) {
        try {
            custId = Integer.parseInt(idParam);
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system", "root", "Soumyajit@123");

            PreparedStatement ps = con.prepareStatement("SELECT * FROM customer WHERE id = ?");
            ps.setInt(1, custId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                fullName = rs.getString("full_name");
                username = rs.getString("username");
                email = rs.getString("email");
                phone = rs.getString("phone");
                address = rs.getString("address");
                dob = rs.getDate("dob");
                gender = rs.getString("gender");
                showForm = true;
            } else {
                out.println("<p style='color:red;'>No customer found with ID " + custId + ".</p>");
            }

            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            out.println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
        }
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Update Customer</title>
    <link rel="stylesheet" href="../css/style.css">
    <style>
        .form-container {
            max-width: 600px;
            margin: auto;
            background: white;
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 0 10px #ccc;
        }
        input, textarea, select {
            width: 100%;
            padding: 10px;
            margin-top: 10px;
        }
        .btn {
            margin-top: 20px;
            padding: 10px;
            background-color: #3498db;
            color: white;
            border: none;
            border-radius: 5px;
        }
        .btn:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body class="centered-bg">
	<div class="overlay"></div>
	<div class="form-container">
    	<h2>Find Customer to Update</h2>
    	<form method="get" action="edit_customer.jsp">
        	<label>Enter Customer ID:</label>
        	<input type="number" name="id" required />
        	<button type="submit" class="btn">Search</button>
    	</form>
	</div>

<% if (showForm) { %>
<div class="form-container">
    <h2>Update Customer Details</h2>
    <form method="post" action="../UpdateCustomer">
        <input type="hidden" name="id" value="<%= custId %>"/>

        <label>Full Name:</label>
        <textarea name="full_name" required><%= fullName %></textarea>

        <label>Username:</label>
        <textarea name="username" required><%= username %></textarea>

        <label>Email:</label>
        <textarea name="email" required><%= email %></textarea>

        <label>Phone:</label>
        <textarea name="phone" required><%= phone %></textarea>

        <label>Address:</label>
        <textarea name="address" required><%= address %></textarea>

        <label>Date of Birth:</label>
        <input type="date" name="dob" value="<%= dob != null ? dob.toString() : "" %>" required />

        <label>Gender:</label>
        <select name="gender" required>
            <option value="Male" <%= "Male".equals(gender) ? "selected" : "" %>>Male</option>
            <option value="Female" <%= "Female".equals(gender) ? "selected" : "" %>>Female</option>
            <option value="Other" <%= "Other".equals(gender) ? "selected" : "" %>>Other</option>
        </select>

        <button type="submit" class="btn">Update</button>
    </form>
</div>
<% } %>

</body>
</html>
