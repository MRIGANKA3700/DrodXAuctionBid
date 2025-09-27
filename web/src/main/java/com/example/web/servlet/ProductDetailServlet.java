package com.example.web.servlet;


import com.example.ee.core.model.Bid;
import com.example.ee.core.model.Product;
import com.example.ee.ejb.remote.BiddingService;
import com.example.ee.ejb.remote.ProductService;
import com.example.ee.ejb.bean.BidServiceBean;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/product")
public class ProductDetailServlet extends HttpServlet {

    @EJB
    private ProductService productService;

    @EJB
    private BiddingService bidServiceBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String productIdParam = request.getParameter("id");
        if (productIdParam != null) {
            try {
                Long productId = Long.parseLong(productIdParam);
                Product product = productService.findProductById(productId);
                List<Bid> bids = bidServiceBean.getBidsForProduct(productId);

                if (product != null) {
                    request.setAttribute("product", product);
                    request.setAttribute("bids", bids);
                    request.getRequestDispatcher("singleproduct.jsp").forward(request, response);
                } else {
                    response.sendRedirect("error.jsp");
                }

            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp");
            }
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
