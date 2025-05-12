<!-- WebContent/pages/staff_register.jsp -->
<!DOCTYPE html>
<html>
<head>
    <title>Staff Registration</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body class="centered-bg">
    <div class="overlay"></div>
    <div class="login-box">
        <h2 class="login-title" style="color: black;">Staff Account Creation</h2>
        <form action="../StaffRegister" method="post">
            <input type="text" name="fullName" placeholder="Full Name" required /><br/>
            <input type="text" name="username" placeholder="Username" required /><br/>
            <input type="email" name="email" placeholder="Email" required /><br/>
            <input type="text" name="phone" placeholder="Phone Number" required /><br/>
            <input type="text" name="branch" placeholder="Branch Assigned" required /><br/>
            <input type="password" name="password" placeholder="Password" required /><br/>
            <input type="password" name="confirm_password" placeholder="Confirm Password" required /><br/>
            <input type="submit" value="Create Staff Account" class="btn" />
        </form>
        <p style="margin-top: 10px; color: black;">
            Already registered? <a href="staff_login.jsp" style="color: blue;">Login</a>
        </p>
    </div>
</body>
</html>
