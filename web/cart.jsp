<%@ page import="dal.ItemDAO" %>
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.DecimalFormat" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Định dạng số
    DecimalFormat dcf = new DecimalFormat("#.##");
    request.setAttribute("dcf", dcf);

    // Lấy auth
    Account auth = (Account) request.getSession().getAttribute("auth");
    if (auth != null) {
        request.setAttribute("person", auth);
    }

    // Lấy cart_list
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    List<Cart> cartProduct = null;
    double total = 0.0;

    if (cart_list != null) {
        ItemDAO pDao = new ItemDAO();
        cartProduct = pDao.getCartProducts(cart_list);
        total = pDao.getTotalCartPrice(cart_list);
        request.setAttribute("total", total);
        request.setAttribute("cart_list", cart_list);
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>E-Commerce Cart</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

    <style>
        /* Tuỳ chỉnh nếu bạn thích */
        .btn-incre, .btn-decre {
            box-shadow: none;
            font-size: 20px;
        }
    </style>
</head>
<body>
    <%@ include file="/includes/navbar.jsp" %>

    <div class="container my-4">
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h3 class="mb-0">
                Tổng tiền: 
                <%= (total > 0) ? (dcf.format(total) + " VND") : "0 VND" %>
            </h3>
            <!-- Form thanh toán toàn bộ -->
            <form action="cart-check-out" method="get">
                <button type="submit" class="btn btn-success">
                    Thanh toán toàn bộ
                </button>
            </form>
        </div>

        <div class="card">
            <div class="card-header">
                <h4 class="mb-0">Giỏ Hàng Của Bạn</h4>
            </div>

            <div class="card-body p-0">
                <table class="table table-bordered mb-0">
                    <thead class="thead-light">
                        <tr>
                            <th scope="col">Tên món</th>
                            <th scope="col">Mô tả</th>
                            <th scope="col">Giá</th>
                            <th scope="col">Mua ngay</th>
                            <th scope="col">Xoá</th>
                        </tr>
                    </thead>
                    <tbody>
                    <%
                        if (cart_list != null && cartProduct != null && !cartProduct.isEmpty()) {
                            for (Cart c : cartProduct) {
                    %>
                        <tr>
                            <td><%= c.getNameItem() %></td>
                            <td><%= c.getDescription() %></td>
                            <td><%= dcf.format(c.getPrice()) %> VND</td>
                            <td>
                                <form action="order-now" method="post" class="form-inline">
						<input type="hidden" name="id" value="<%= c.getItemId()%>" class="form-input">
							<div class="form-group d-flex justify-content-between">
                                                            <a class="btn btn-sm btn-decre" href="quantity-inc-dec?action=dec&id=<%=c.getItemId()%>"><i class="fas fa-minus-square"></i></a>
								<input type="text" name="quantity" class="form-control"  value="<%=c.getQuantity()%>" readonly> 
                                                            <a class="btn bnt-sm btn-incre" href="quantity-inc-dec?action=inc&id=<%=c.getItemId()%>"><i class="fas fa-plus-square"></i></a> 
								
								
							</div>
							<button type="submit" class="btn btn-primary btn-sm">Buy</button>
						</form>
                            </td>
                            <td>
                                <a href="remove-from-cart?id=<%= c.getItemId() %>"
                                   class="btn btn-sm btn-danger">
                                   Xóa
                                </a>
                            </td>
                        </tr>
                    <%
                            }
                        } else {
                    %>
                        <tr>
                            <td colspan="5" class="text-center">
                                Giỏ hàng trống
                            </td>
                        </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div><!-- card-body end -->
        </div><!-- card end -->
    </div><!-- container end -->
<%@include file="/includes/footer.jsp"%>
    <!-- Bootstrap JS (nếu bạn cần) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Nếu muốn icon fontawesome cho <i class="fas fa-plus-square">, import fontawesome -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/js/all.min.js"></script>
</body>
</html>
