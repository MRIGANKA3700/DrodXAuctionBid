package com.example.web.servlet;

import com.example.ee.core.model.Customer;
import com.example.ee.ejb.remote.CustomerService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @EJB
    private CustomerService customerService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usernameOrEmail = request.getParameter("username");
        if (usernameOrEmail != null) {
            usernameOrEmail = usernameOrEmail.trim();
        }
        String password = request.getParameter("password");

        Customer customer = customerService.login(usernameOrEmail, password);

        if (customer != null) {
            HttpSession session = request.getSession();
            session.setAttribute("username", customer.getUsername());
            session.setAttribute("user", customer);

            // Check if user was redirected here from singleproduct.jsp
            String redirect = request.getParameter("redirect");
            String productId = request.getParameter("id");
            if (redirect != null && redirect.equals("product") && productId != null) {
                response.sendRedirect("product?id=" + productId);
            } else {
                // Redirect to /products servlet, which loads products and forwards to index.jsp
                response.sendRedirect(request.getContextPath() + "/products");
            }

        } else {
            request.setAttribute("loginError", "Invalid username/email or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }



    }
}
