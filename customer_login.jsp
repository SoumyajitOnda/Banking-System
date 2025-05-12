<!-- WebContent/pages/admin_login.jsp -->
<!DOCTYPE html>
<html>
<head>
    <title>Customer Login</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body class="centered-bg">
    <div class="overlay"></div>
    <div class="login-box">
        <h2 class="login-title" style="color: black;">Customer Login</h2>
        <form action="../CustomerLogin" method="post">
            <input type="text" name="username" placeholder="Username" required /><br/>
            <input type="password" name="password" placeholder="Password" required /><br/>
            <input type="submit" value="Login" class="btn"/>
            <p style="margin-top: 10px; color: black;">
            Don't have an account? <a href="customer_register.jsp" style="color: blue;">Sign up</a>
</p>   
        </form>
    </div>
</body>
</html>
