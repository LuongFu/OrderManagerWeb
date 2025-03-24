<%@ page import="model.Account, java.util.ArrayList, model.Cart" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    // Kiểm tra login. Nếu đã login -> chuyển hướng index
    Account auth = (Account) request.getSession().getAttribute("auth");
    if (auth != null) {
        response.sendRedirect("index.jsp");
        return;
    }

    // Lấy cart_list
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if (cart_list != null) {
        request.setAttribute("cart_list", cart_list);
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng Nhập</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

    <!-- Optional: Fontawesome (nếu bạn cần icon user, lock, v.v.) -->
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

    <style>
        /* Background image (the same as your old code) */
        body {
            font-family: 'Poppins', sans-serif;
            background: url('images/mau me.jpg') no-repeat center center;
            background-size: cover;
            min-height: 100vh;
        }
        .login-wrapper {
            max-width: 400px;
            margin: 0 auto;
            padding: 30px 25px;
            background: rgba(255, 255, 255, 0.1); /* 1 chút trong suốt */
            backdrop-filter: blur(10px);
            border-radius: 10px;
            margin-top: 5%; /* đẩy form xuống 1 chút */
        }
        .login-wrapper .form-control {
            background-color: rgba(255, 255, 255, 0.2);
            border: none;
            color: #fff;
        }
        .login-wrapper .form-control::placeholder {
            color: #fff;
        }
        .login-wrapper input[type="checkbox"] {
            accent-color: #fff;
        }
        .login-wrapper label {
            color: #fff;
        }
        .login-wrapper .btn.btn-light {
            background: #fff;
            color: #333;
            border-radius: 30px;
        }
        .error {
            color: red;
        }
    </style>
</head>
<body>
    <!-- Sử dụng container Bootstrap -->
    <div class="container">
        <div class="login-wrapper">
            <!-- Form login -->
            <form action="login" method="post">
                <h1 class="text-center text-white mb-4">Login</h1>

                <!-- Nếu có error -->
                <c:if test="${requestScope.error != null}">
                    <p class="error text-center mb-3">${requestScope.error}</p>
                </c:if>

                <!-- Username -->
                <div class="form-group">
                    <label for="username">Username</label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text bg-transparent border-0 text-white">
                                <i class="fas fa-user"></i>
                            </span>
                        </div>
                        <input type="text" class="form-control"
                               id="username" name="username" placeholder="Username" required>
                    </div>
                </div>

                <!-- Password -->
                <div class="form-group">
                    <label for="password">Password</label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text bg-transparent border-0 text-white">
                                <i class="fas fa-lock"></i>
                            </span>
                        </div>
                        <input type="password" class="form-control"
                               id="password" name="password" placeholder="Password" required>
                    </div>
                </div>

                <!-- Remember checkbox -->
                <div class="form-group form-check d-flex justify-content-between">
                    <label class="form-check-label">
                        <input type="checkbox" class="form-check-input"
                               name="rememberMe" value="true">
                        Remember me
                    </label>
                    <!-- <a href="#" class="text-white">Forgot Password?</a> -->
                </div>

                <!-- Nút submit -->
                <button type="submit" class="btn btn-light btn-block">Login</button>

                <!-- Link đăng ký -->
                <div class="text-center mt-3 text-white">
                    <p class="mb-0">
                        Don't have an account?
                        <a href="register.jsp" class="text-white font-weight-bold">
                            Sign up.
                        </a>
                    </p>
                </div>
            </form>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
