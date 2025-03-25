<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Món Ăn</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <a href="logout" class="nav-link logout-link">Sign out</a>
        <form action="item-manager?action=UPDATE" method="POST">
            <input type="hidden" name="itemId" value="${item.itemId}">
            <div class="form-group">
                <label for="nameItem">Tên món:</label>
                <input type="text" class="form-control" id="nameItem" name="nameItem" value="${item.nameItem}"required>
            </div>

            <div class="form-group">
                <label for="price">Giá:</label>
                <input type="number" class="form-control" id="price" name="price" step="1000.0" value="${item.price}"required>
            </div>

            <div class="form-group">
                <label for="description">Mô tả:</label>
                <textarea class="form-control" id="description" name="description" required>${item.description}</textarea>
            </div>
            
            <div class="form-group">
                <label for="createdBy">Tạo bởi:</label>
                <input type="text" class="form-control" id="createdBy" name="createdBy" value="${item.createBy}"required>
            </div>
            
            <div class="form-group">
                <label for="image">Chọn hình ảnh:</label>
                <select class="form-control" id="image" name="image" required>
                    <option value="logo.png">Default Logo</option>
                    <option value="ca_phe.jpg">Cà Phê</option>
                    <option value="matcha_latte.jpg">Matcha latte</option>
                    <option value="pho.jpg">Phở</option>
                    <option value="bun_gio.jpg">Bún</option>
                    <option value="mi_quang.jpg">Mì Quảng</option>
                    <option value="banh_bao.jpg">Bánh bao</option>
                    <option value="cappuccino.jpg">Cappuccino</option>
                    <option value="tra_sua.jpg">Trà Sữa</option>
                    <option value="ga.jpg">Gà</option>
                    <option value="nem_chua.jpg">Nem chua</option>
                    <option value="goi_cuon.jpg">Gỏi</option>
                    <option value="nem_ran.jpg">Nem rán</option>
                    <option value="kem.jpg">Kem</option>
                </select>
            </div>

            <button type="submit" class="btn btn-primary">Cập nhật</button>
        </form>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
