<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng Ký</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' type='text/css' media='screen' href='styles.css'>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
</head>
<body>
    <div class="wrapper">
        <h1>Sign Up</h1>
        <form action="register" method="post">
            <div class="input-box">
                <input type="text" name="username" placeholder="Username" required>
                <i class="bx bx-user"></i>
            </div>
            <div class="input-box">
                <input type="email" name="email" placeholder="Email" required>
                <i class="bx bx-envelope"></i>
            </div>
            <div class="input-box">
                <input type="password" name="password" placeholder="Password" required>
                <i class="bx bx-lock-alt"></i>
            </div>
            <div class="input-box">
                <input type="password" name="confirm_password" placeholder="Confirm Password" required>
                <i class="bx bx-lock-alt"></i>
            </div>
            <div class="input-box">
                <input type="tel" name="phone" placeholder="Phone" required>
                <i class="bx bx-phone"></i>
            </div>
            <div class="remember-forgot">
                <label><input type="checkbox" required> I agree to the terms & policies</label>
            </div>
            <button type="submit" class="btn">Sign Up</button>
            <div class="register-link">
                <p>Already have account? <a href="login.jsp">Sign In</a></p>
            </div>
        </form>
    </div>
</body>
</html>
