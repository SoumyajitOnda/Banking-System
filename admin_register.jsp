<!-- WebContent/pages/admin_register.jsp -->
<!DOCTYPE html>
<html>
<head>
    <title>Manager Account Creation</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body class="centered-bg">
    <div class="overlay"></div>
    <div class="login-box">
        <h2 class="login-title" style="color: black;">Manager Registration</h2>
        <form action="../AdminRegister" method="post">
            <input type="text" name="username" placeholder="Username" required /><br/>
            <input type="password" name="password" placeholder="Password" required /><br/>
            <input type="password" name="confirm_password" placeholder="Confirm Password" required /><br/>
            <input type="submit" value="Register Manager" class="btn" />
        </form>
        <p style="margin-top: 10px; color: black;">
            Already registered? <a href="admin_login.jsp" style="color: blue;">Login</a>
        </p>
    </div>
</body>
</html>
