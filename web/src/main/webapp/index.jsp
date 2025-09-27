<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DrodX AuctionBid - Home</title>
    <link rel="stylesheet" href="style.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <meta http-equiv="refresh" content="10"> <!-- refresh every 10 seconds -->

</head>
<body class="hp-body">
<header class="hp-header">
    <div class="hp-container">
        <div class="hp-logo">
            <i class="fas fa-gavel"></i>
            <span>DrodX</span>
        </div>
        <nav class="hp-nav">
            <ul>
                <li><a href="#" class="hp-active">Home</a></li>
                <li><a href="#">Live Auctions</a></li>
                <li><a href="#">Categories</a></li>
                <li><a href="#">My Bids</a></li>
                <li><a href="#">Sell Item</a></li>
            </ul>
        </nav>
        <%
            String username = (String) session.getAttribute("username");
        %>
        <div class="hp-user-actions">
            <% if (username != null) { %>
            <span class="hp-welcome-text">Welcome, <%= username %>!</span>
            <a href="logout" class="hp-btn hp-btn-outline">Logout</a>

            <% } else { %>
            <a href="login.jsp" class="hp-btn hp-btn-outline">Sign In</a>
            <a href="register.jsp" class="hp-btn hp-btn-primary">Register</a>
            <% } %>
        </div>

    </div>
</header>

<main class="hp-container">
    <section class="hp-hero">
        <h1>Discover Amazing Auctions</h1>
        <p>Bid on unique items from around the world</p>
        <div class="hp-search-bar">
            <input type="text" placeholder="Search for items...">
            <button class="hp-btn hp-btn-primary"><i class="fas fa-search"></i> Search</button>
        </div>
    </section>

    <section class="hp-auction-grid">
        <h2 class="hp-section-title">Featured Auctions</h2>
        <%@ page import="java.util.List" %>
        <%@ page import="com.example.ee.core.model.Product" %>
        <%
            if (request.getAttribute("products") == null) {
                response.sendRedirect("products");
                return;
            }
        %>



<%--        <%--%>
<%--            String username = (String) session.getAttribute("username");--%>
<%--            if (username == null) {--%>
<%--                response.sendRedirect("login.jsp");--%>
<%--                return;--%>
<%--            }--%>
<%--        %>--%>

        <%
            List<Product> products = (List<Product>) request.getAttribute("products");
        %>
        <div class="hp-auction-cards">
            <%
                if (products != null && !products.isEmpty()) {
                    for (Product product : products) {
            %>
            <div class="hp-auction-card">
                <div class="hp-card-image">
                    <img src="<%= product.getImageUrl() %>" alt="<%= product.getTitle() %>">
                    <div class="hp-time-left"><%= product.getEndTime() %></div>
                </div>
                <div class="hp-card-content">
                    <h3 class="hp-item-title"><%= product.getTitle() %></h3>
                    <p class="hp-item-description"><%= product.getDescription() %></p>
                    <div class="hp-bid-info">
                        <div class="hp-current-bid">
                            <span>Current Bid:</span>
                            <span class="hp-price">$<%= product.getCurrentBid() %></span>
                        </div>
                        <div class="hp-bids-count">
                            <i class="fas fa-gavel"></i> <%= product.getBidCount() %> bids
                        </div>
                    </div>
<%--                    <button class="hp-btn hp-btn-bid" onclick="location.href='product?id=<%= product.getId() %>'">--%>
<%--                        Place Bid--%>
<%--                    </button>--%>
                    <%
                        if (username != null) {
                    %>
                    <button class="hp-btn hp-btn-bid" onclick="location.href='product?id=<%= product.getId() %>'">
                        Place Bid
                    </button>
                    <%
                    } else {
                    %>
                    <button class="hp-btn hp-btn-bid" onclick="redirectToLogin('<%= product.getId() %>')">
                        Place Bid
                    </button>
                    <%
                        }
                    %>

                </div>
            </div>
            <%
                }
            } else {
            %>
            <p>No auctions available at the moment.</p>
            <%
                }
            %>


        </div>
    </section>
</main>

<footer class="hp-footer">
    <div class="hp-container">
        <div class="hp-footer-content">
            <div class="hp-footer-section">
                <h3>DrodX AuctionBid</h3>
                <p>The premier online auction platform for unique items and collectibles.</p>
            </div>
            <div class="hp-footer-section">
                <h3>Quick Links</h3>
                <ul>
                    <li><a href="#">About Us</a></li>
                    <li><a href="#">How It Works</a></li>
                    <li><a href="#">FAQ</a></li>
                    <li><a href="#">Contact</a></li>
                </ul>
            </div>
            <div class="hp-footer-section">
                <h3>Connect With Us</h3>
                <div class="hp-social-icons">
                    <a href="#"><i class="fab fa-facebook"></i></a>
                    <a href="#"><i class="fab fa-twitter"></i></a>
                    <a href="#"><i class="fab fa-instagram"></i></a>
                </div>
            </div>
        </div>
        <div class="hp-copyright">
            <p>&copy; 2025 DrodX AuctionBid. All rights reserved.</p>
        </div>
    </div>
</footer>
<script>
    function redirectToLogin(productId) {
        alert('Please login to place a bid.');
        window.location.href = 'login.jsp?redirect=product&id=' + productId;
    }

    setTimeout(function() {
        location.reload();
    }, 10000); // every 10 seconds
</script>

</body>
</html>