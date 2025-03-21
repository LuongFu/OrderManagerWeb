<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Thống kê</title>
        <script src="script.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body class="container mt-5">
        <h2 class="text-center">Thống Kê Người Dùng</h2>

        <ul>
            <li id="refresh">Số lần refresh: </li>
            <li id="addItem">Số lần thêm món: </li>
            <li id="deleteItem">Số lần xóa món: </li>
            <li>Số lần đăng nhập: ${applicationScope.loginCount}</li>
        </ul>

        <script>
            document.getElementById("refresh").innerText += localStorage.getItem("refreshCount") || 0;
            document.getElementById("addItem").innerText += localStorage.getItem("addItemCount") || 0;
            document.getElementById("deleteItem").innerText += localStorage.getItem("deleteItemCount") || 0;
        </script>


    </body>
</html>
