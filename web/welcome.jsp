<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' type='text/css' media='screen' href='styles.css'>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <title>Chào Mừng</title>
</head>
<body>
    <h2>Chào mừng, ${sessionScope.session_login}</h2>
    <p>Bạn đã đăng nhập thành công.</p>
    <form action="LogoutServlet" method="get">
        <a><a href="logout" class="logout-link">Đăng Xuất</a>
    </form>
</body>
</html>
