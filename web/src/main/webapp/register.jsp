<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DrodX Auctions - Register</title>
    <link rel="stylesheet" href="style.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body  class="auth-body">
<div class="auth-container">
    <div class="auth-box">
    <div class="auth-header">
        <div class="logo">
            <i class="fas fa-gavel"></i>
            <span>DrodX</span>
        </div>
        <h1>Join DrodX Auctions</h1>
        <p>Create your account to start bidding on exclusive items</p>
    </div>

    <form action="register" method="post" class="auth-form">

        <div class="form-group">
            <label for="username">Username</label>
            <div class="input-with-icon">
                <i class="fas fa-user"></i>
                <input type="text" id="username" name="username" required placeholder="Enter your username"
                       value="<%= request.getParameter("username") != null ? request.getParameter("username") : "" %>">
            </div>
            <span class="error-message" style="color:red;">
                <%= request.getAttribute("usernameError") != null ? request.getAttribute("usernameError") : "" %>
            </span>
        </div>

        <div class="form-group">
            <label for="email">Email</label>
            <div class="input-with-icon">
                <i class="fas fa-envelope"></i>
                <input type="email" id="email" name="email" required placeholder="Enter your email"
                       value="<%= request.getParameter("email") != null ? request.getParameter("email") : "" %>">
            </div>
            <span class="error-message" style="color:red;">
                <%= request.getAttribute("emailError") != null ? request.getAttribute("emailError") : "" %>
            </span>
        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <div class="input-with-icon">
                <i class="fas fa-lock"></i>
                <input type="password" id="password" name="password" required placeholder="Create a password">
            </div>
            <span class="error-message" style="color:red;">
                <%= request.getAttribute("passwordError") != null ? request.getAttribute("passwordError") : "" %>
            </span>
        </div>

        <div class="form-group">
            <label for="confirm-password">Confirm Password</label>
            <div class="input-with-icon">
                <i class="fas fa-lock"></i>
                <input type="password" id="confirm-password" name="confirm-password" required placeholder="Confirm your password">
            </div>
            <span class="error-message" style="color:red;">
                <%= request.getAttribute("confirmPasswordError") != null ? request.getAttribute("confirmPasswordError") : "" %>
            </span>
        </div>

        <div class="form-group">
            <label for="phone">Phone Number</label>
            <div class="input-with-icon">
                <i class="fas fa-phone"></i>
                <input type="tel" id="phone" name="phone" placeholder="Optional phone number"
                       value="<%= request.getParameter("phone") != null ? request.getParameter("phone") : "" %>">
            </div>
        </div>

        <%-- Optional general registration error (e.g., if CustomerStore fails) --%>
        <span class="error-message" style="color:red;">
            <%= request.getAttribute("registrationError") != null ? request.getAttribute("registrationError") : "" %>
        </span>

        <div class="form-group checkbox-group terms-group">
            <input type="checkbox" id="terms" name="terms" required>
            <label for="terms">I agree to the <a href="#">Terms of Service</a> and <a href="#">Privacy Policy</a></label>
        </div>

        <button type="submit" class="auth-btn">Create Account</button>

        <div class="auth-link">
            Already have an account? <a href="login.jsp">Sign in</a>
        </div>
    </form>
</div>
</div>

</body>
</html>
