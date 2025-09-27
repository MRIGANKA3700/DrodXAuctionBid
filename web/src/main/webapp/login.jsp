


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DrodX Auctions - Login</title>
    <link rel="stylesheet" href="style.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>

<body class="auth-body">
<div class="auth-container">
    <div class="auth-box">
        <header class="navbar">
            <div class="logo">
                <i class="fas fa-gavel"></i> DrodX Auctions
            </div>
            <nav>
                <a href="index.jsp">Home</a>
            </nav>
        </header>
        <div class="auth-header">
            <h1>Welcome to DrodX Auctions</h1>
            <p>Sign in to access your account</p>
        </div>

    <form action="login" method="post" class="auth-form">
        <%-- Hidden fields for redirect parameters --%>
        <input type="hidden" name="redirect" value="<%= request.getParameter("redirect") != null ? request.getParameter("redirect") : "" %>">
        <input type="hidden" name="id" value="<%= request.getParameter("id") != null ? request.getParameter("id") : "" %>">

        <div class="form-group">
            <label for="username">Username or Email</label>
            <div class="input-with-icon">
                <i class="fas fa-user"></i>
                <input type="text" id="username" name="username" required placeholder="Enter your username or email"
                       value="<%= request.getParameter("username") != null ? request.getParameter("username") : "" %>">
            </div>
            <span class="error-message" style="color:red;">
                <%= request.getAttribute("usernameError") != null ? request.getAttribute("usernameError") : "" %>
            </span>
        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <div class="input-with-icon">
                <i class="fas fa-lock"></i>
                <input type="password" id="password" name="password" required placeholder="Enter your password">
                <a href="forgot-password.jsp" class="forgot-password">Forgot password?</a>
            </div>
            <span class="error-message" style="color:red;">
                <%= request.getAttribute("passwordError") != null ? request.getAttribute("passwordError") : "" %>
            </span>
        </div>

        <%-- Optional login failure message --%>
        <span class="error-message" style="color:red;">
            <%= request.getAttribute("loginError") != null ? request.getAttribute("loginError") : "" %>
        </span>

        <div class="form-group remember-group">
            <input type="checkbox" id="remember" name="remember"
                <%= request.getParameter("remember") != null ? "checked" : "" %>>
            <label for="remember">Remember me</label>
        </div>

        <button type="submit" class="auth-btn">Sign In</button>

        <div class="auth-link">
            Don't have an account? <a href="register.jsp">Register now</a>
        </div>
    </form>
</div>
</div>
<script>
    const url = new URL(window.location);
    if (url.searchParams.get('registered') === 'true') {
        alert('Registration successful! You can now log in.');
        // Remove query param
        url.searchParams.delete('registered');
        window.history.replaceState({}, document.title, url.pathname);
    }
</script>
</body>
</html>


