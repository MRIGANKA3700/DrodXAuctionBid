

<%@ page import="com.example.ee.core.model.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>




<!DOCTYPE html>
<html lang="en">
<head>
    <%
        Product product = (Product) request.getAttribute("product");
        if (product != null) {
    %>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DrodX AuctionBid - <%= product != null ? product.getTitle() : "Product" %></title>
    <link rel="stylesheet" href="style.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

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
                <li><a href="index.jsp">Home</a></li>
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
    <%
        String bidMessage = (String) session.getAttribute("bidMessage");
        if (bidMessage != null) {
    %>
    <div class="hp-flash-message">
        <p><%= bidMessage %></p>
    </div>
    <%
            session.removeAttribute("bidMessage"); // Remove after showing once
        }
    %>
    <div class="hp-product-container">
        <div class="hp-product-content">
            <div class="hp-product-image">
                <img src="<%= product.getImageUrl() %>" alt="<%= product.getTitle() %>">
            </div>

            <div class="hp-product-details">
                <h1 class="hp-product-title"><%= product.getTitle() %></h1>
                <div class="hp-seller-info">
                    <span>Seller: <strong>Disandu Rodrigo</strong></span>
                    <span class="hp-verified-badge"><i class="fas fa-check-circle"></i> Verified</span>
                </div>
                <div class="hp-product-description">
                    <span>Description:</span>
                    <p><%= product.getDescription() %></p>
                </div>


                <div class="hp-bid-info-grid">

<%--                    <p>Current Highest Bid: <span id="currentBid">$<%= product.getCurrentBid() %></span></p>--%>



                    <div class="hp-bid-info-item">
                        <span>Current Bid:</span>
                        <strong id="currentBid" class="hp-price">$<%= product.getCurrentBid() %></strong>
                    </div>
                    <div class="hp-bid-info-item">
                        <span>Ending Bid:</span>
                        <strong>$100</strong>
                    </div>
                    <div class="hp-bid-info-item">
                        <span>Bids:</span>
                        <strong>5</strong>
                    </div>
                    <div class="hp-bid-info-item">
                        <span>Time Left:</span>
                        <%@ page import="java.text.SimpleDateFormat" %>
                        <%@ page import="com.example.ee.core.model.Bid" %>
                        <%@ page import="java.util.List" %>
                        <%@ page import="java.text.SimpleDateFormat" %>
                        <%
                            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy HH:mm");
                        %>

                        <%
                            SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
                            String endTimeStr = isoFormat.format(product.getEndTime());
                        %>
                        <strong class="hp-auction-ended" id="countdown" data-endtime="<%= endTimeStr %>">Loading...</strong>


                    </div>
                </div>

                <div class="hp-bid-section">
                    <h3>Place Your Bid</h3>
                    <div class="hp-bid-quick-actions">
                        <button class="hp-btn hp-btn-bid-quick" onclick="updateBidAmount(<%= product.getCurrentBid() +100 %>)">+ $100</button>
                        <button class="hp-btn hp-btn-bid-quick" onclick="updateBidAmount(<%= product.getCurrentBid() +250%>)">+ $250</button>
                        <button class="hp-btn hp-btn-bid-quick" onclick="updateBidAmount(<%= product.getCurrentBid() +500%>)">+ $500</button>
                    </div>

                    <form action="placeBid" method="post" class="hp-bid-form" onsubmit="return validateAutoBidAmount()">

                        <input type="hidden" id="pid" value="<%= product.getId() %>">
                    <input type="hidden" name="productId" value="<%= product.getId() %>">
                        <div class="hp-form-group">
                            <div class="hp-input-with-currency">
                                <span class="hp-currency-symbol">$</span>
                                <input type="number" id="bidAmount" name="bidAmount"
                                       min="<%= product.getCurrentBid() + 50 %>"
                                       step="100.00"
                                       value="<%= product.getCurrentBid() + 50 %>" required>
                            </div>
                        </div>

                        <div class="hp-autobid-section">
                            <div class="hp-form-check">
                                <input type="checkbox" id="enableAutoBid" name="enableAutoBid" onchange="toggleAutoBidOptions()">
                                <label for="enableAutoBid">Enable Auto Bidding</label>
                            </div>

                            <div id="autoBidOptions" style="display: none; margin-top: 10px;">
                                <div class="hp-form-group">
                                    <input type="number" id="maxAutoBid" name="maxAutoBid" placeholder="Enter max autobid" class="hp-input-field">
                                </div>

                                <div class="hp-toggle-container">
                                    <label class="hp-toggle-switch">
                                        <input type="checkbox" id="autoBidSwitch" name="autoBid">
                                        <span class="hp-toggle-slider"></span>
                                    </label>
                                    <span class="hp-toggle-label">Enable Auto Bid </span>
                                </div>
                            </div>
                        </div>


                        <div class="warning-box">
                            ⚠️
                            <div class="hp-form-check">

                                <label>
                                    By placing a bid, you agree to our Terms of Service and confirm this is a binding offer.
                                </label>
                            </div>
                        </div>

                        <button type="submit" class="hp-btn hp-btn-bid hp-place-bid-btn" id="placeBidBtn">Place Bid</button>
                    </form>
                </div>
            </div>
        </div>

        <div class="hp-bidding-history">
            <h3>Bidding History</h3>
            <div class="hp-history-items">
                <!-- Example history items -->
                <%
                    List<Bid> bids = (List<Bid>) request.getAttribute("bids");
                    if (bids != null && !bids.isEmpty()) {
                        for (Bid bid : bids) {
                %>
                <div class="hp-history-item">
                    <span class="hp-history-bidder"><%= bid.getUsername() %></span>
                    <span class="hp-history-amount">$<%= bid.getAmount() %></span>
                    <span class="hp-history-time"><%= sdf.format(bid.getTimestamp()) %></span>

                </div>
                <%
                    }
                } else {
                %>
                <p>No bids yet. Be the first to bid!</p>
                <%
                    }
                %>


            </div>
        </div>
    </div>
    <% } else { %>
    <div class="hp-error-message">
        <h2>Product Not Found</h2>
        <p>The product you're looking for doesn't exist or has been removed.</p>
        <a href="index.jsp" class="hp-btn hp-btn-primary">Back to Home</a>
    </div>
    <% } %>
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



        document.addEventListener("DOMContentLoaded", startCountdown);

        function startCountdown() {
            const countdownEl = document.getElementById("countdown");
            const endTimeStr = countdownEl.getAttribute("data-endtime");
            const endTime = new Date(endTimeStr).getTime();

            const interval = setInterval(() => {
                const now = new Date().getTime();
                const distance = endTime - now;

                if (distance <= 0) {
                    clearInterval(interval);
                    countdownEl.textContent = "Auction Ended";
                    countdownEl.classList.add("hp-auction-ended");

                    // Disable the bidding inputs
                    const bidAmount = document.getElementById("bidAmount");
                    const autoBid = document.getElementById("autoBid");
                    const placeBidBtn = document.getElementById("placeBidBtn");

                    if (bidAmount) bidAmount.disabled = true;
                    if (autoBid) autoBid.disabled = true;
                    if (placeBidBtn) {
                        placeBidBtn.disabled = true;
                        placeBidBtn.classList.add("hp-disabled");
                        placeBidBtn.textContent = "Bidding Closed";
                    }

                    return;
                }



                const days = Math.floor(distance / (1000 * 60 * 60 * 24));
                const hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                const minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
                const seconds = Math.floor((distance % (1000 * 60)) / 1000);

                let display = '';
                if (days > 0) display += days + "d ";
                display += hours + "h " + minutes + "m " + seconds + "s";

                countdownEl.textContent = display;
            }, 1000);
        }


        const productId = "<%= product.getId() %>";
        const currentBidEl = document.getElementById("currentBid");
        const bidInput = document.getElementById("bidAmount");

        const socket = new WebSocket("ws://" + window.location.host + "/ee-app/bidSocket");

        socket.onopen = function () {
            console.log("WebSocket connected for product ID: " + productId);
        };

        socket.onmessage = function (event) {
            const data = event.data.split(":");
            const receivedProductId = data[0];
            const bidAmount = data[1];
            const username = data[2];

            if (receivedProductId === productId) {
                const amount = parseFloat(bidAmount).toFixed(2);
                currentBidEl.textContent = "$" + amount;
                bidInput.min = parseFloat(amount) + 50;
                bidInput.value = parseFloat(amount) + 50;

                // Add new bid to the top of bidding history
                const historyContainer = document.querySelector(".hp-history-items");
                const bidItem = document.createElement("div");
                bidItem.className = "hp-history-item";
                const now = new Date();
                const formattedTime = now.toLocaleString(); // e.g., "6/2/2025, 10:35:12 AM"

                bidItem.innerHTML = `
        <span class="hp-history-bidder">${username}</span>
        <span class="hp-history-amount">$${amount}</span>
        <span class="hp-history-time">${formattedTime}</span>
    `;

                // Prepend new bid
                historyContainer.prepend(bidItem);


                // Remove "No bids yet" message if it exists
                const noBidsMsg = document.querySelector(".hp-history-items p");
                if (noBidsMsg) {
                    noBidsMsg.remove();
                }

            }

        };

        socket.onerror = function (error) {
            console.error("WebSocket error:", error);
        };

        function updateBidAmount(amount) {
            document.getElementById("bidAmount").value = amount;
        }

        function toggleAutoBidOptions() {
            const checkBox = document.getElementById("enableAutoBid");
            const optionsDiv = document.getElementById("autoBidOptions");
            optionsDiv.style.display = checkBox.checked ? "block" : "none";
        }


        document.getElementById('autoBidSwitch').addEventListener('change', function () {
            const isAutoBidEnabled = this.checked;
            const maxAutoBid = parseFloat(document.getElementById("maxAutoBid").value);
            const currentBid = parseFloat(document.getElementById("currentBid").textContent.replace("$", ""));

            if (isAutoBidEnabled) {
                if (isNaN(maxAutoBid)) {
                    alert("Please enter a valid max auto bid amount.");
                    return;
                }

                if (maxAutoBid <= currentBid) {
                    alert("Max auto bid must be greater than the current bid ($" + currentBid + ").");
                    return;
                }

                console.log("Auto-bid started with Max Bid:", maxAutoBid);
                PlaceAutoBid();
            } else {
                console.log("Auto-bid stopped");
                DisableAutoBid(); // You can implement this as needed
            }
        });


        async function PlaceAutoBid() {
            const maxBidLimit = document.getElementById('maxAutoBid').value;
            const currentBid = document.getElementById("currentBid").textContent.replace("$", "");
            const productId = document.getElementById("pid").value;

            const formData = new URLSearchParams();
            formData.append("bid", maxBidLimit);
            formData.append("pid", productId);
            formData.append("currentBid", currentBid);

            try {
                const response = await fetch("/ee-app/placeAutoBid", {
                    method: "POST",
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded',
                    },
                    body: formData.toString()
                });

                const result = await response.text();
                alert(result); // Or show this in a better UI location
            } catch (error) {
                console.error("Failed to save auto-bid:", error);
                alert("Failed to save auto-bid.");
            }
        }

        async function DisableAutoBid() {
            const productId = document.getElementById("pid").value;

            const formData = new URLSearchParams();
            formData.append("pid", productId);

            try {
                const response = await fetch("/ee-app/disableAutoBid", {
                    method: "POST",
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded',
                    },
                    body: formData.toString()
                });

                const result = await response.text();
                alert(result); // Optional: display user feedback
            } catch (error) {
                console.error("Failed to disable auto-bid:", error);
                alert("Failed to disable auto-bid.");
            }
        }



</script>
</body>
</html>