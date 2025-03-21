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

            <button type="submit" class="btn btn-primary">Cập nhật</button>
        </form>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
