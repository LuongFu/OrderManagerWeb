<%@ page import="dal.ItemDAO" %>
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    Account auth = (Account) request.getSession().getAttribute("auth");
    int userId = -1; // Gán mặc định
    if (auth != null) {
        userId = auth.getUserId();
        request.setAttribute("person", auth);
        request.setAttribute("userId", userId);
    }

    ItemDAO pd = new ItemDAO();
    List<Item> products = pd.getAllItem();

    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if (cart_list != null) {
        request.setAttribute("cart_list", cart_list);
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Menu</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

    <!-- Optionally, fontawesome if you need icons, e.g. for <i class="..."> -->
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
    <%@ include file="/includes/navbar.jsp" %> <!-- Nếu bạn có navbar -->

    <div class="container my-4">
        <h2>Chào mừng ${sessionScope.session_login}</h2>

        <div class="card mt-3">
            <div class="card-header">
                <h4 class="mb-0">All Products</h4>
            </div>
            <div class="card-body">
                <div class="row">
                    <%
                    if (!products.isEmpty()) {
                        for (Item p : products) {
                    %>
                    <div class="col-md-3 mb-4">
                        <div class="card h-100">
                            <div class="card-body d-flex flex-column">
                                <h5 class="card-title"><%= p.getNameItem() %></h5>
                                <img class="card-img-top" width="auto" height="100px" src="food/<%=p.getImage() %>"
                                     alt="Card image cap">
                                <h6 class="card-subtitle mb-2 text-muted">
                                    Price: <%= p.getPrice() %> VND
                                </h6>
                                <p class="card-text">
                                    Description: <%= p.getDescription() %>
                                </p>
                                <div class="mt-auto d-flex justify-content-between">
                                    <!-- Thêm vào giỏ hàng -->
                                    <a class="btn btn-dark"
                                       href="add-to-cart?id=<%= p.getItemId() %>">
                                       Thêm vào giỏ hàng
                                    </a>

                                    <!-- Thanh toán -->
                                    
                                        <div class="mt-3 d-flex justify-content-between">
                              <a
                                    class="btn btn-primary" href="order-now?quantity=1&id=<%=p.getItemId()%>">Buy Now</a>
                            </div>
                                    
                                </div>
                            </div>
                        </div>
                    </div>
                    <%
                        }
                    } else {
                    %>
                    <div class="col-12">
                        <p>Không có sản phẩm.</p>
                    </div>
                    <%
                    }
                    %>
                </div> <!-- row end -->
            </div> <!-- card-body end -->
        </div> <!-- card end -->
    </div> <!-- container end -->

    <!-- Bootstrap JS (nếu bạn cần tính năng JS) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
