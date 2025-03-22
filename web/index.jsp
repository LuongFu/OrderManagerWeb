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
int userId = -1; // GÁN GIÁ TRỊ MẶC ĐỊNH ngay lúc khai báo ở đây

if (auth != null) {
    userId = auth.getUserId(); // nếu có auth thì sẽ ghi đè userId
    request.setAttribute("person", auth);
    request.setAttribute("userId", userId);
}
%>

<%
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
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <title>E-Commerce Cart</title>
    </head>
    <body>
        <%@include file="/includes/navbar.jsp"%>
        <h2>Chào mừng ${sessionScope.session_login}</h2>
        <!--<p>Bạn đã đăng nhập thành công.</p>-->
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
                                <a class="btn btn-dark" href="add-to-cart?id=<%=p.getItemId()%>">Thêm vào giỏ hàng  </a> 
                                <a class="btn btn-primary"
                                   href="vnpayajax?totalBill=<%= p.getPrice() %>&bankCode=VNPAYQR">
                                    Thanh toán
                                </a>




                                <script>
                                    function submitBuyNow(itemId, quantity) {
                                        const form = document.createElement("form");
                                        form.method = "POST";
                                        form.action = "order-now";

                                        const idInput = document.createElement("input");
                                        idInput.type = "hidden";
                                        idInput.name = "id";
                                        idInput.value = itemId;

                                        const qtyInput = document.createElement("input");
                                        qtyInput.type = "hidden";
                                        qtyInput.name = "quantity";
                                        qtyInput.value = quantity;

                                        form.appendChild(idInput);
                                        form.appendChild(qtyInput);

                                        document.body.appendChild(form);
                                        form.submit();
                                    }
                                </script>
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
