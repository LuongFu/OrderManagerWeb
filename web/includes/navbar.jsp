<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
        <a class="navbar-brand" href="index.jsp">FapFood</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="support.jsp">Support</a></li>
                <li class="nav-item"><a class="nav-link" href="menu.jsp">Menu</a>
                <li class="nav-item"><a class="nav-link" href="cart.jsp">Cart <span class="badge badge-danger">${cart_list.size()}</span> </a></li>
                    <%
                    if (auth != null) {
                    %>
                
                <li class="nav-item"><a class="nav-link" href="orders.jsp">Orders</a></li>
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
