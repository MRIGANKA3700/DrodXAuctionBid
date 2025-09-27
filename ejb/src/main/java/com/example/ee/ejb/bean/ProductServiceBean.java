
package com.example.ee.ejb.bean;

import com.example.ee.core.model.AutoBid;
import com.example.ee.core.model.Customer;
import com.example.ee.core.model.Product;
import com.example.ee.ejb.remote.ProductService;
import jakarta.ejb.Singleton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class ProductServiceBean implements ProductService {

    private final List<Product> products = new ArrayList<>();
    private ConcurrentHashMap<Integer, List<AutoBid>> autoBidConfigerMap;


    public ProductServiceBean() {


        autoBidConfigerMap = new ConcurrentHashMap<>();

        Date now = new Date();

        products.add(new Product(1L, "Samsung S24 Ultra", "Latest Samsung Flagship Smartphone", "images/Samsung S24 Ultra.jpg", 2400.00, 15, addMinutes(now, 9000)));
        products.add(new Product(2L, "Apple MacBook Pro M6", "16-inch MacBook with M6 Chip", "images/Apple MacBook Pro M6.jpg", 1600.00, 32, addMinutes(now, 0)));
        products.add(new Product(3L, "Abstract Oil Painting", "Original artwork...", "images/Abstract Oil Painting.jpg", 4800.00, 5, addMinutes(now, 4920)));
        products.add(new Product(4L, "Apple Watch Series 7", "Brand new, unopened...", "images/Apple Watch Series 7.jpg", 320.00, 8, addMinutes(now, 6180)));
        products.add(new Product(5L, "Vintage Leica Camera", "Rare 1950s Leica M3...", "images/Vintage Leica Camera.jpg", 3900.00, 35, addMinutes(now, 3120)));
        products.add(new Product(6L, "Samsung S23 Note", "Latest Samsung Note Series Flagship product", "images/Samsung S23 Note.jpg", 1250.00, 12, addMinutes(now, 3120)));
        products.add(new Product(7L, "Antique Windsor Chair", "19th century original...", "images/Antique Windsor Chai.jpg", 1750.00, 21, addMinutes(now, 4560)));
        products.add(new Product(8L, "Apple iPhone 15", "Latest Apple Flagship product", "images/Apple iPhone 15.jpg", 1250.00, 17, addMinutes(now, 5160)));
        products.add(new Product(9L, "Sony Xperia 10 VI", "One of the Best Sony Smartphone", "images/Sony Xperia 10 VI.jpg", 2800.00, 23, addMinutes(now, 560)));
    }

    private Date addMinutes(Date start, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }

    @Override
    public void replaceProduct(Product product) {
        products.removeIf(p -> p.getId().equals(product.getId()));
        products.add(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public Product findProductById(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void registersAutoBidConfiger(AutoBid config) {
        autoBidConfigerMap.computeIfAbsent(config.getProductId(), k -> new ArrayList<>()).add(config);
    }

    @Override
    public List<AutoBid> getAutoBiddersConfigerForProducts(int productId) {
        return autoBidConfigerMap.getOrDefault(productId, new ArrayList<>());
    }

    @Override
    public Customer getUserById(Long userId) {
//        for (Customer customer : Customer.getAllCustomers()) {
//            if (customer.getId().equals(userId)) {
//                return customer;
//            }
//        }
        return null;
    }

    @Override
    public void removeAutoBid(Long productId, Long userId) {
        List<AutoBid> configs = autoBidConfigerMap.get(productId.intValue());
//        if (configs != null) {
//            synchronized (configs) {
//                configs.removeIf(config -> config.getUserId().equals(getUserById()));
//            }
//        }
    }

}
