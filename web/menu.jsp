<%@ page import="dal.ItemDAO" %>
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    Account auth = (Account) request.getSession().getAttribute("auth");
    
    if(auth != null){
    int userId = auth.getUserId();
//    String cashString = auth.getCash();
//    double cash = Double.parseDouble(cashString);
    request.setAttribute("userId", userId);
    }
    

    ItemDAO itemDAO = new ItemDAO();
    List<Item> allItems = itemDAO.getAllItem();
    int itemsPerPage = 12;
    int totalItems = allItems.size();
    int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);

    int currentPage = 1;
    if (request.getParameter("page") != null) {
        currentPage = Integer.parseInt(request.getParameter("page"));
    }

    int startIndex = (currentPage - 1) * itemsPerPage;
    int endIndex = Math.min(startIndex + itemsPerPage, totalItems);
    List<Item> paginatedItems = allItems.subList(startIndex, endIndex);
%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Menu</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <link rel="stylesheet" type="text/css" href="css/menu-style.css">
    </head>
    <body>
        <%@ include file="/includes/navbar.jsp" %>
        <div class="container my-4">
            <h2>Chào mừng ${sessionScope.session_login}</h2>
            <div class="card mt-3">
                
        <!-- Add more functionality if needed -->
                <div class="card-header">
                    <h4 class="mb-0">All Products</h4>
                </div>
                <div class="card-body">
                    <div class="row">
                        <% for (Item item : paginatedItems) { %>
                        <div class="col-md-3 mb-4">
                            <div class="card h-100">
                                <img class="card-img-top" src="food/<%= item.getImage() %>" alt="<%= item.getNameItem() %>">
                                <div class="card-body d-flex flex-column">
                                    <h5 class="card-title"><%= item.getNameItem() %></h5>
                                    <h6 class="card-subtitle mb-2 text-muted">Price: <%= item.getPrice() %> VND</h6>
                                    <p class="card-text"><%= item.getDescription() %></p>
                                    <div class="btn-container mt-auto">
                                        <a class="btn btn-dark" href="add-to-cart?id=<%= item.getItemId() %>"><i class="fas fa-cart-plus"></i> Add to cart</a>
                                        <a class="btn btn-primary" href="order-now?quantity=1&id=<%= item.getItemId() %>"><i class="fas fa-bolt"></i> Buy Now</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <% } %>
                    </div>

                    <!-- Pagination -->
                    <nav>
                        <ul class="pagination justify-content-center">
                            <li class="page-item <%= (currentPage == 1) ? "disabled" : "" %>">
                                <a class="page-link" href="menu.jsp?page=1">&laquo;</a>
                            </li>
                            <li class="page-item <%= (currentPage == 1) ? "disabled" : "" %>">
                                <a class="page-link" href="menu.jsp?page=<%= currentPage - 1 %>">&lsaquo;</a>
                            </li>
                            <% for (int i = 1; i <= totalPages; i++) { %>
                            <li class="page-item <%= (i == currentPage) ? "active" : "" %>">
                                <a class="page-link" href="menu.jsp?page=<%= i %>"><%= i %></a>
                            </li>
                            <% } %>
                            <li class="page-item <%= (currentPage == totalPages) ? "disabled" : "" %>">
                                <a class="page-link" href="menu.jsp?page=<%= currentPage + 1 %>">&rsaquo;</a>
                            </li>
                            <li class="page-item <%= (currentPage == totalPages) ? "disabled" : "" %>">
                                <a class="page-link" href="menu.jsp?page=<%= totalPages %>">&raquo;</a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
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
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
