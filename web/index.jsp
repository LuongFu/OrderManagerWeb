<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="dal.ItemDAO" %>
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<%@ include file="includes/head.jsp" %> <!-- Include header.jsp -->
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
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FapFood - Canteen Ordering System</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        /* Custom Styles */
        body {  
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            background-image: url('images/food-banner.png');
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
        }
        .navbar {
            background: rgba(255, 102, 0, 0.2) !important;
            backdrop-filter: blur(6px);
            transition: background 0.3s, backdrop-filter 0.3s;
        }
        .navbar.scrolled {
            background: rgba(255, 102, 0, 0.6) !important;
        }
        .navbar-brand img {
            height: 50px;
            transition: transform 0.3s;
        }
        .navbar-brand img:hover {
            transform: scale(1.1);
        }
        .hero-section {
            display: flex;
            align-items: center;
            justify-content: center;
            min-height: 80vh;
            text-align: center;
            padding: 50px 0;
            color: white;
        }
        .hero-text h1 {
            font-size: 8rem;
            font-family: 'Lobster', cursive;
            color: orange;
            text-shadow: 6px 6px 20px rgba(255, 140, 0, 0.9);
            letter-spacing: 3px;
        }
        .hero-text p {
            font-size: 1.2rem;
            font-family: 'Playfair Display', serif;
            font-weight: 500;
            color: #ffffff;
            text-shadow: 2px 2px 6px rgba(0, 0, 0, 0.5);
        }
    </style>
</head>
<body>
    <!-- Navigation bar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
        <div class="container-fluid">
            <a class="navbar-brand me-auto d-flex align-items-center">
                <img src="images/logo.png" alt="FapFood Logo">
                <img src="images/logo2.png" alt="FapFood Second Logo">
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-center" id="navbarNav">
                <ul class="navbar-nav gap-4">
                    <!-- Add menu items here if needed -->
                </ul>
            </div>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="support.jsp">Support</a></li>
                <li class="nav-item"><a class="nav-link" href="menu.jsp">Menu</a></li>
                <li class="nav-item"><a class="nav-link" href="orders.jsp">Orders</a></li>
                    <%
                    if (auth != null) {
                    %>
                <li class="nav-item"><a href="logout" class="nav-link logout-link">Sign out</a>
                </li>
                <%
                } else {
                %>
                <li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
                    <%
                    }
                    %>
            </ul>
        </div>
        </div>
    </nav>

    <!-- Hero Section -->
    <section class="container hero-section d-flex flex-wrap" style="background-image: url('images/food-banner.png'); background-size: cover; background-position: center;">
        <div class="col-md-6 hero-text text-center">
            <h2>Fast, convenient, and delicious !!!</h2>
            <h1 class="brand-name">FapFood</h1>
            <p class="welcome-message">
                Welcome to the FapFood Canteen Ordering System, your gateway to a world of flavors !!!
            </p>
        </div>
    </section>

    <%@ include file="includes/footer.jsp" %> <!-- Include footer.jsp -->

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>