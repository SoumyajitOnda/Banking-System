<!-- WebContent/pages/customer_register.jsp -->
<!DOCTYPE html>
<html>
<head>
    <title>Customer Account Registration</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body class="centered-bg">
    <div class="overlay"></div>
    <div class="login-box">
        <h2 class="login-title" style="color: black;">Customer Account Creation</h2>
        <form action="../CustomerRegister" method="post">
            <input type="text" name="fullName" placeholder="Full Name" required /><br/>
            <input type="text" name="username" placeholder="Username" required /><br/>
            <input type="email" name="email" placeholder="Email" required /><br/>
            <input type="text" name="phone" placeholder="Phone Number" required /><br/>
            <input type="text" name="address" placeholder="Address" required /><br/>
            <input type="date" name="dob" placeholder="Date of Birth" required /><br/>
            <select name="gender" required>
                <option value="" disabled selected>Gender</option>
                <option>Male</option>
                <option>Female</option>
                <option>Other</option>
            </select><br/>
            <input type="password" name="password" placeholder="Password" required /><br/>
            <input type="password" name="confirm_password" placeholder="Confirm Password" required /><br/>
            <input type="submit" value="Create Account" class="btn" />
        </form>
        <p style="margin-top: 10px; color: black;">
            Already have an account? <a href="customer_login.jsp" style="color: blue;">Login</a>
        </p>
    </div>
</body>
</html>
