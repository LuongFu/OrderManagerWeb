<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.text.DecimalFormat" %>
<%
    // Định dạng số
    DecimalFormat dcf = new DecimalFormat("#.##");
    double totalAmount = (Double) request.getAttribute("totalAmount");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
            <div class="container my-4">
        <h2 class="mb-3">Total Sales of All Orders</h2>
        <p>Total Amount of All Orders: <%= dcf.format(totalAmount) %> VND</p>
    </div>

        <script>
            document.getElementById("refresh").innerText += localStorage.getItem("refreshCount") || 0;
            document.getElementById("addItem").innerText += localStorage.getItem("addItemCount") || 0;
            document.getElementById("deleteItem").innerText += localStorage.getItem("deleteItemCount") || 0;
        </script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>


    </body>
</html>
