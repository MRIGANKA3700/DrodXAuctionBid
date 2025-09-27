package com.example.web.servlet;

import com.example.ee.core.model.AutoBid;
import com.example.ee.core.model.Customer;
import com.example.ee.ejb.remote.ProductService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;

import static java.lang.Integer.parseInt;


@WebServlet("/disableAutoBid")
public class DisableAutoBidServlet  extends HttpServlet{

    @EJB
    ProductService productService;




   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String productId = request.getParameter("pid");
        Customer customer = (Customer) request.getSession().getAttribute("user");


        productService.removeAutoBid((long) parseInt(productId), customer.getId());
        response.getWriter().write("Auto-bid configuration saved successfully.");

    }
}