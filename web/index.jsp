<%-- 
    Document   : index
    Created on : Mar 14, 2025, 2:41:47 PM
    Author     : Fu
--%>
<%@page import="dal.ItemDAO"%>
<%@page import="model.*"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
Account auth = (Account) request.getSession().getAttribute("auth");
if (auth != null) {
    request.setAttribute("person", auth);
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
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<link rel='stylesheet' type='text/css' media='screen' href='styles.css'>-->
        <link rel="preconnect" href="https://fonts.googleapis.com">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <title>E-Commerce Cart</title>
    </head>
    <body>
        <%@include file="/includes/navbar.jsp"%>
        <h2>Chào mừng ${sessionScope.session_login}</h2>
    <!--<p>Bạn đã đăng nhập thành công.</p>-->
    <form action="LogoutServlet" method="get">
        <a><a href="logout" class="logout-link">Đăng Xuất</a>
    </form>

        <div class="container">
            <div class="card-header my-3">All Products</div>
            <div class="row">
                <%
                if (!products.isEmpty()) {
                        for (Item p : products) {
                %>
                <div class="col-md-3 my-3">
                    <div class="card w-100">
                        <div class="card-body">
                            <h5 class="card-title"><%=p.getNameItem() %></h5>
                            <h6 class="price">Price: <%=p.getPrice() %> VND</h6>
                            <h6 class="description">Description: <%=p.getDescription() %></h6>
                            <div class="mt-3 d-flex justify-content-between">
                                <a class="btn btn-dark" href="add-to-cart?id=<%=p.getItemId()%>">Add to Cart</a> <a
                                    class="btn btn-primary" href="order-now?quantity=1&id=<%=p.getItemId()%>">Buy Now</a>
                            </div>
                        </div>
                    </div>
                </div>
                <%
                }
                } else {
                out.println("There is no products");
                }
                %>

            </div>
        </div>
        

        
    </body>
</html>
