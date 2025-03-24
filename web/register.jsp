<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đăng Ký</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

    <!-- Font Awesome (nếu bạn cần icon) -->
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

    <style>
        /* Nếu bạn muốn dùng background cũ */
        body {
            background: url('images/mau me.jpg') no-repeat center center;
            background-size: cover;
            min-height: 100vh;
            font-family: 'Poppins', sans-serif;
        }
        .register-wrapper {
            max-width: 450px;
            margin: 0 auto;
            margin-top: 5%;
            background: rgba(255, 255, 255, 0.1);
            backdrop-filter: blur(10px);
            border-radius: 10px;
            padding: 30px;
            color: #fff;
        }
        /* Tùy chỉnh form control */
        .register-wrapper .form-control {
            background-color: rgba(255, 255, 255, 0.2);
            border: none;
            color: #fff;
        }
        .register-wrapper .form-control::placeholder {
            color: #fff;
        }
        .register-wrapper input[type="checkbox"] {
            accent-color: #fff; /* modern browsers */
        }
        .register-wrapper label {
            color: #fff;
        }
        .register-wrapper .btn.btn-light {
            background: #fff;
            color: #333;
            border-radius: 30px;
        }
        .register-link a {
            color: #fff;
            font-weight: 600;
            text-decoration: none;
        }
        .register-link a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="register-wrapper">
            <h1 class="text-center mb-4">Sign Up</h1>

            <form action="register" method="post">
                <!-- Username -->
                <div class="form-group">
                    <label for="username">Username</label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text bg-transparent border-0 text-white">
                                <i class="fas fa-user"></i>
                            </span>
                        </div>
                        <input type="text" 
                               class="form-control"
                               id="username"
                               name="username"
                               placeholder="Username"
                               required>
                    </div>
                </div>

                <!-- Email -->
                <div class="form-group">
                    <label for="email">Email</label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text bg-transparent border-0 text-white">
                                <i class="fas fa-envelope"></i>
                            </span>
                        </div>
                        <input type="email"
                               class="form-control"
                               id="email"
                               name="email"
                               placeholder="Email"
                               required>
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
                        <input type="password"
                               class="form-control"
                               id="password"
                               name="password"
                               placeholder="Password"
                               required>
                    </div>
                </div>

                <!-- Confirm Password -->
                <div class="form-group">
                    <label for="confirm_password">Confirm Password</label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text bg-transparent border-0 text-white">
                                <i class="fas fa-lock"></i>
                            </span>
                        </div>
                        <input type="password"
                               class="form-control"
                               id="confirm_password"
                               name="confirm_password"
                               placeholder="Confirm Password"
                               required>
                    </div>
                </div>

                <!-- Phone -->
                <div class="form-group">
                    <label for="phone">Phone</label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text bg-transparent border-0 text-white">
                                <i class="fas fa-phone"></i>
                            </span>
                        </div>
                        <input type="tel"
                               class="form-control"
                               id="phone"
                               name="phone"
                               placeholder="Phone"
                               required>
                    </div>
                </div>

                <!-- Terms checkbox -->
                <div class="form-group form-check">
                    <input type="checkbox" class="form-check-input" id="agree" required>
                    <label class="form-check-label" for="agree">
                        I agree to the terms &amp; policies
                    </label>
                </div>

                <!-- Submit button -->
                <button type="submit" class="btn btn-light btn-block">
                    Sign Up
                </button>

                <!-- Link đăng nhập -->
                <div class="register-link text-center mt-3">
                    <p class="mb-0">
                        Already have account?
                        <a href="login.jsp">Sign In</a>
                    </p>
                </div>
            </form>
        </div><!-- register-wrapper -->
    </div><!-- container -->

    <!-- Bootstrap JS (nếu cần) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
