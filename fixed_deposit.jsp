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
    <title>Fixed Deposit</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body class="centered-bg">
    <div class="overlay"></div>
    <div class="login-box">
        <h2 style="color: black;">Open a Fixed Deposit</h2>

        <!-- Displaying feedback messages based on the 'msg' parameter -->
        <div>
            <% 
                String msg = request.getParameter("msg");
                if (msg != null) {
                    switch (msg) {
                        case "success":
                            out.println("<p style='color:green;'>Fixed deposit created successfully!</p>");
                            break;
                        case "error":
                            out.println("<p style='color:red;'>An error occurred while creating the fixed deposit. Please try again.</p>");
                            break;
                        case "emptyfields":
                            out.println("<p style='color:red;'>Please fill in all the fields.</p>");
                            break;
                        case "invalidinput":
                            out.println("<p style='color:red;'>Invalid input. Please enter valid data.</p>");
                            break;
                        case "failed":
                            out.println("<p style='color:red;'>Failed to create the fixed deposit. Please try again.</p>");
                            break;
                    }
                }
            %>
        </div>

        <form action="../FixedDepositServlet" method="post">
            <input type="hidden" name="username" value="<%= username %>" />
            <input type="number" name="amount" placeholder="Amount Rs." required />
            <input type="number" name="duration" placeholder="Duration (months)" required />
            <input type="submit" class="btn" value="Create FD" />
        </form>

        <a href="customer_dashboard.jsp" class="btn" style="margin-top: 15px;">Back to Dashboard</a>
    </div>
</body>
</html>
