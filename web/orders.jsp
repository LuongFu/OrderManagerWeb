<%@ page import="model.*" %>
<%@ page import="dal.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    DecimalFormat dcf = new DecimalFormat("#.##");
    request.setAttribute("dcf", dcf);
    Account auth = (Account) request.getSession().getAttribute("auth");
    List<Order> orders = null;
    if (auth != null) {
        request.setAttribute("person", auth);
        OrderDAO orderDAO = new OrderDAO();
        orders = orderDAO.userOrders(auth.getUserId());
    } else {
        response.sendRedirect("login.jsp");
    }
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if (cart_list != null) {
        request.setAttribute("cart_list", cart_list);
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Your Orders</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="css/cart-style.css"> <!-- Custom CSS -->
</head>
<body>
    <%@ include file="/includes/navbar.jsp" %> <!-- Navbar -->

    <div class="container my-4">
        <h2>Your Orders</h2>

        <!-- Display Orders -->
        <table class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th scope="col">Order ID</th>
                    <th scope="col">Item Name</th>
                    <th scope="col">Quantity</th>
                    <th scope="col">Price</th>
                    <th scope="col">Status</th>
                    <th scope="col">Date</th>
                </tr>
            </thead>
            <tbody>
                <%
                    if (orders != null && !orders.isEmpty()) {
                        for (Order order : orders) {
                %>
                <tr>
                    <td><%= order.getOrderId() %></td>
                    <td><%= order.getNameItem() %></td>
                    <td><%= order.getQuantity() %></td>
                    <td><%= dcf.format(order.getPrice()) %> VND</td>
                    <td><%= order.getStatus() %></td>
                    <td><%= order.getDate() %></td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="6" class="text-center">You have no orders yet.</td>
                </tr>
                <% 
                    }
                %>
            </tbody>
        </table>
    </div>

    <!-- Bootstrap JS (if needed) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
