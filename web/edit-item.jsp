<%-- 
    Document   : edit-item
    Created on : Mar 14, 2025, 2:33:40 PM
    Author     : Fu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="item-manager" method="post" enctype="multipart/form-data">
            <input type="hidden" name="action" value="add">
            <input type="text" name="nameItem" placeholder="Item Name" required><br>
            <input type="number" name="price" placeholder="Price" required><br>
            <input type="text" name="description" placeholder="Description" required><br>
            <input type="text" name="createdBy" placeholder="Created By" required><br>
            <input type="file" name="image" required><br>  <!-- For uploading an image -->
            <button type="submit">Edit Item</button>
        </form>
    </body>
</html>
