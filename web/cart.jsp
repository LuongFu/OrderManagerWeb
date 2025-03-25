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
        <link rel="stylesheet" type="text/css" href="css/cart-style.css">
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
        <footer class="footer">
            <div class="container">
                <div class="footer-content">
                    <div class="company-info" style="flex: 6;">
                        <p><strong>CÔNG TY TNHH VPHK</strong></p>
                        <p><strong>CHI NHÁNH ĐÀ NẴNG</strong></p>
                        <p>Số ĐKKD: 0315367026-001 - Nơi cấp: Sở kế hoạch và đầu tư Tp. Đà Nẵng</p>
                        <p>Đăng ký lần đầu ngày 15/03/2022</p>
                        <p>Địa chỉ: 123 Nguyễn Văn Linh, P. Hải Châu 1, Q. Hải Châu, Tp. Đà Nẵng</p>

                    </div>
                    <div class="partners" style="flex: 4;">
                        <h4>ĐỐI TÁC</h4>
                        <div class="partner-logos" style="display: grid; grid-template-columns: repeat(5, 1fr); gap: 10px;">
                            <img src="images/logo_0_0.png" alt="Beta Cinemas">
                            <img src="images/logo_0_1.png" alt="Mega GS">
                            <img src="images/logo_0_2.png" alt="CineStar">
                            <img src="images/logo_1_0.png" alt="Dcine">
                            <img src="images/logo_1_1.png" alt="Cinemax">
                            <img src="images/logo_1_2.png" alt="Starlight">
                            <img src="images/logo_2_0.png" alt="Rio Cinemas">
                            <img src="images/logo_2_1.png" alt="Touch Cinema">
                            <img src="images/logo_2_2.png" alt="Momo">
                            <img src="images/logo.png" alt="Another Brand">
                        </div>
                    </div>
                    <div class="certification" style="flex: 2; display: flex; align-items: center; justify-content: center;">
                        <img src="images/bo-cong-thuong.png" alt="Bộ Công Thương">
                    </div>
                </div>
            </div>
        </footer>
        <!-- Bootstrap JS (nếu bạn cần) -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Nếu muốn icon fontawesome cho <i class="fas fa-plus-square">, import fontawesome -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/js/all.min.js"></script>
    </body>
</html>
