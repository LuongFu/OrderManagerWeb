<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="dal.ItemDAO" %>
<%@ page import="model.Item" %>
<%@ page import="dto.Response" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Item Manager</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <h2>Hello, admin ${sessionScope.session_login}</h2>
    <body class="container mt-4">
        <h2 class="text-center">Item Manager</h2>

        <!--        <form action="item-manager" method="post" class="row g-3">
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
        
                    <div class="col-md-6">
                        <label class="form-label">Upload Image</label>
                        <input type="file" name="image" value="${item.getImage()}" class="form-control" required>
                    </div>
        
                    <div class="col-md-12 text-center">
                        <button type="submit" class="btn btn-primary">Add Item</button>
                    </div>
                </form>-->
        <a href="add-item.jsp">Lead to item manager</a>

        <h3 class="mt-4 text-center">Item List</h3>

        <div class="table-responsive">
            <table class="table table-bordered table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Description</th>
                        <th>Created By</th>
                        <th>Size</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach var="item" items="${sessionScope.ListItem}">
                        <tr>
                            <td>${item.getItemId()}</td>
                            <td>${item.getNameItem()}</td>
                            <td>${item.getPrice()}</td>
                            <td>${item.getDescription()}</td>
                            <td>${item.getCreateBy()}</td>
                            <td>${item.getSize()}</td>
                            <td>
                                <form action="item-manager" method="post" class="d-inline">
                                    <input type="hidden" name="action" value="edit">
                                    <input type="hidden" name="idItem" value="${item.getItemId()}">
                                    <button type="submit" class="btn btn-warning btn-sm">Edit</button>
                                </form>
                                <form action="item-manager" method="post" class="d-inline">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="idItem" value="${item.getItemId()}">
                                    <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete?')">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>


                </tbody>
            </table>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
