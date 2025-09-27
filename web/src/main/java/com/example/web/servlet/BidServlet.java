
package com.example.web.servlet;

import com.example.ee.core.model.Bid;
import com.example.ee.core.model.Customer;
import com.example.ee.core.model.Product;

import com.example.ee.ejb.remote.BiddingService;
import com.example.ee.ejb.remote.ProductService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/placeBid")
public class BidServlet extends HttpServlet {

    @EJB
    private ProductService productService;

    @EJB
    private BiddingService bidServiceBean;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);
        Customer user = (Customer) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String productIdParam = request.getParameter("productId");
        String bidAmountParam = request.getParameter("bidAmount");

        if (productIdParam != null && bidAmountParam != null) {
            try {
                Long productId = Long.parseLong(productIdParam);
                double bidAmount = Double.parseDouble(bidAmountParam);
                Product product = productService.findProductById(productId);

                if (product != null) {
                    List<Bid> existingBids = bidServiceBean.getBidsForProduct(productId);
                    double currentHighestBid = product.getCurrentBid();
                    if (!existingBids.isEmpty()) {
                        currentHighestBid = existingBids.stream()
                                .mapToDouble(Bid::getAmount)
                                .max()
                                .orElse(product.getCurrentBid());
                    }
                    if (bidAmount > currentHighestBid) {

                        Bid newBid = new Bid(productId, user.getUsername(), bidAmount, new Date());
                        bidServiceBean.addBid(newBid);


                        product.setCurrentBid(bidAmount);
                        product.setBidCount(existingBids.size() + 1);

                        productService.replaceProduct(product);

                        System.out.println("Updated product in list: " + product.getId());

                        response.sendRedirect("product?id=" + productId);
                        return;
                    } else {
                        request.setAttribute("error", "Bid amount must be higher than current bid.");
                        request.getRequestDispatcher("singleproduct.jsp?id=" + productId)
                                .forward(request, response);
                        return;
                    }
                }
                response.sendRedirect("error.jsp");
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp");
            }
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
