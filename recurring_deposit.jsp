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
    <title>Create Recurring Deposit</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body class="centered-bg">
    <div class="overlay"></div>
    <div class="login-box">
        <h2>Open a Recurring Deposit Account</h2>
        <div>
            <% 
                String msg = request.getParameter("msg");
                if (msg != null) {
                    switch (msg) {
                        case "success":
                            out.println("<p style='color:green;'>Recurring deposit created successfully!</p>");
                            break;
                        case "error":
                            out.println("<p style='color:red;'>An error occurred while creating the Recurring deposit. Please try again.</p>");
                            break;
                    }
                }
            %>
        </div>
        <form action="../RecurringDepositServlet" method="post">
            <input type="hidden" name="username" value="<%= username %>" />
            <input type="number" name="monthly_deposit" placeholder="Monthly Deposit Rs." required />
            <input type="number" name="interest_rate" placeholder="Interest Rate %" required />
            <input type="number" name="duration" placeholder="Duration (Months)" required />
            <input type="submit" class="btn" value="Create RD" />
        </form>
        <a href="customer_dashboard.jsp" class="btn" style="margin-top: 15px;">Back to Dashboard</a>
    </div>
</body>
</html>
