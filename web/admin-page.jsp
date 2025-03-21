<%-- 
    Document   : admin-page
    Created on : Feb 21, 2025, 11:29:16 AM
    Author     : Fu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
    </head>
    <body>
        <h2>Hello, ${sessionScope.session_login}</h2>
        
        <a href="item-manager?action=LIST"> Lead to item manager</a>
    </body>
</html>
