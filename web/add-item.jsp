<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add Item</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="container mt-4">
    <h2 class="text-center">Add New Item</h2>

             <form action="item-manager" method="post" class="row g-3">
                    <input type="hidden" name="action" value="${requestScope.item != null ? 'update' : 'add'}">
                    <input type="hidden" name="action" value="${item.getItemId()}" value="add">
        
                    <div class="col-md-6">
                        <label class="form-label">Name:</label>
                        <input type="text" name="nameItem" value="${item.getNameItem()}" class="form-control" required>
                    </div>
        
                    <div class="col-md-6">
                        <label class="form-label">Price:</label>
                        <input type="number" name="price" value="${item.getPrice()}" class="form-control" step="1000" required>
                    </div>
        
                    <div class="col-md-6">
                        <label class="form-label">Description:</label>
                        <input type="text" name="description" value="${item.getDescription()}" class="form-control" required>
                    </div>
        
                    <div class="col-md-6">
                        <label class="form-label">Created By:</label>
                        <input type="text" name="createdBy" value="${item.getCreateBy()}" class="form-control" required>
                    </div>
        
                    <div class="col-md-12 text-center">
                        <button type="submit" class="btn btn-primary">Add Item</button>
                    </div>
                </form>

    <!-- Display any success or error messages -->
    <c:if test="${not empty message}">
        <div class="alert alert-success">
            ${message}
        </div>
    </c:if>
    
    <c:if test="${not empty error}">
        <div class="alert alert-danger">
            ${error}
        </div>
    </c:if>

    <hr>

    <div class="text-center">
        <a href="item-manager.jsp" class="btn btn-secondary">Back to Item Manager</a>
    </div>

</body>
</html>
