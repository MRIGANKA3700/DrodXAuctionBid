package com.example.web.servlet;



import com.example.ee.core.model.Product;
import com.example.ee.ejb.remote.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;
import jakarta.ejb.EJB;
import jakarta.servlet.http.*;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    @EJB
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Fetch product list from EJB instead of hardcoding
        List<Product> productList = productService.getAllProducts();

        request.setAttribute("products", productList);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
