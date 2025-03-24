<%@ page import="java.text.DecimalFormat" %>
<%@ page import="dal.OrderDAO" %>
<%@ page import="dal.ItemDAO" %>
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    DecimalFormat dcf = new DecimalFormat("#.##");
    request.setAttribute("dcf", dcf);

    Account auth = (Account) request.getSession().getAttribute("auth");
    List<Order> orders = null;

    if (auth == null) {
        response.sendRedirect("login.jsp");
        return;
    } else {
        request.setAttribute("person", auth);
        OrderDAO orderDAO = new OrderDAO();
        orders = orderDAO.userOrders(auth.getUserId());
    }

    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if (cart_list != null) {
        request.setAttribute("cart_list", cart_list);
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Orders</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
</head>
<body>
    <!-- Navbar, nếu có -->
    <%@ include file="/includes/navbar.jsp" %>

    <div class="container my-4">
        <h2 class="mb-3">
            Lịch sử đơn hàng của 
            <%= (auth != null) ? auth.getUsername() : "Khách" %>
        </h2>

        <div class="card">
            <div class="card-header">
                <h4 class="mb-0">All Orders</h4>
            </div>
            <div class="card-body p-0">
                <table class="table table-bordered table-hover mb-0">
                    <thead class="thead-light">
                        <tr>
                            <th scope="col">Date</th>
                            <th scope="col">Name</th>
                            <th scope="col">Description</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Price</th>
                            <th scope="col">Hủy đơn</th>
                        </tr>
                    </thead>
                    <tbody>
                    <%
                        if (orders != null) {
                            for (Order o : orders) {
                    %>
                        <tr>
                            <td><%= o.getDate() %></td>
                            <td><%= o.getNameItem() %></td>
                            <td><%= o.getImage() %></td>
                            <td><%= o.getQuantity() %></td>
                            <td><%= dcf.format(o.getPrice()) %> VND</td>
                            <td>
                                <a class="btn btn-sm btn-danger"
                                   href="cancel-order?id=<%= o.getOrderId() %>">
                                   Cancel Order
                                </a>
                            </td>
                        </tr>
                    <%
                            }
                        } else {
                    %>
                        <tr>
                            <td colspan="6" class="text-center">
                                Bạn chưa có đơn hàng nào.
                            </td>
                        </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div> <!-- card-body -->
        </div> <!-- card -->
    </div> <!-- container -->

    <!-- Bootstrap JS (nếu bạn cần tính năng JS) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
