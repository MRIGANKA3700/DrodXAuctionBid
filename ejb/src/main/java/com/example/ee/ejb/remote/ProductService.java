package com.example.ee.ejb.remote;

import com.example.ee.core.model.AutoBid;
import com.example.ee.core.model.Customer;
import com.example.ee.core.model.Product;
import jakarta.ejb.Remote;

import java.util.List;

@Remote

public interface ProductService {
    List<Product> getAllProducts();
    Product findProductById(Long id);

    void replaceProduct(Product product);

    void registersAutoBidConfiger(AutoBid config);
    List<AutoBid> getAutoBiddersConfigerForProducts(int productId);
    Customer getUserById(Long userId);
    void removeAutoBid(Long productId, Long userId);
}

