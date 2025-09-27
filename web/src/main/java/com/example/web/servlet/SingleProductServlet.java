package com.example.web.servlet;


import com.example.ee.core.model.Product;
import com.example.ee.ejb.remote.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.ejb.EJB;
import java.io.IOException;
import jakarta.servlet.http.*;


@WebServlet("/product-detail")
public class SingleProductServlet extends HttpServlet {

    @EJB
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");

        if (idParam != null) {
            try {
                Long id = Long.parseLong(idParam);
                Product product = productService.findProductById(id);
                request.setAttribute("product", product);

            } catch (NumberFormatException e) {
                request.setAttribute("product", null);
            }
        } else {
            request.setAttribute("product", null);
        }

        request.getRequestDispatcher("singleproduct.jsp").forward(request, response);
    }
}
