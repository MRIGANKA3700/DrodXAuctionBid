package com.example.web.servlet;

import com.example.ee.core.model.Customer;
import com.example.ee.ejb.remote.CustomerService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @EJB
    private CustomerService customerService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username").trim();
        String email = request.getParameter("email").trim();
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm-password");
        String phone = request.getParameter("phone");

        boolean hasError = false;

        if (customerService.isUsernameTaken(username)) {
            request.setAttribute("usernameError", "Username is already taken");
            hasError = true;
        }

        if (customerService.isEmailTaken(email)) {
            request.setAttribute("emailError", "Email is already registered");
            hasError = true;
        }

        if (password == null || password.length() < 6) {
            request.setAttribute("passwordError", "Password must be at least 6 characters");
            hasError = true;
        }

        if (!password.equals(confirmPassword)) {
            request.setAttribute("confirmPasswordError", "Passwords do not match");
            hasError = true;
        }

        if (hasError) {
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        Customer customer = new Customer(null, username, email, password, phone);
        boolean success = customerService.registerCustomer(customer);

        if (success) {
            response.sendRedirect("login.jsp?registered=true");
        } else {
            request.setAttribute("registrationError", "Registration failed. Try again.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}