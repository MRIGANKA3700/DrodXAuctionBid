package com.example.ee.ejb.bean;


import com.example.ee.core.model.Customer;
import com.example.ee.ejb.remote.CustomerService;
import jakarta.ejb.Singleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class CustomerServiceBean implements CustomerService {

    private final Map<String, Customer> userStore = new HashMap<>();
    private final Map<String, Customer> emailStore = new HashMap<>();
    private final Map<Long, Customer> idStore = new HashMap<>();


    private long nextId = 1L;

    @Override
    public boolean registerCustomer(Customer customer) {
        if (userStore.containsKey(customer.getUsername()) || emailStore.containsKey(customer.getEmail())) {
            return false;
        }


        customer.setId(nextId++);

        userStore.put(customer.getUsername(), customer);
        emailStore.put(customer.getEmail(), customer);
        idStore.put(customer.getId(), customer);
        return true;
    }



    @Override
    public Customer login(String usernameOrEmail, String password) {
        Customer customer = userStore.getOrDefault(usernameOrEmail, emailStore.get(usernameOrEmail));
        if (customer != null && customer.getPassword().equals(password)) {
            return customer;
        }
        return null;
    }

    @Override
    public boolean isUsernameTaken(String username) {
        return userStore.containsKey(username);
    }

    @Override
    public boolean isEmailTaken(String email) {
        return emailStore.containsKey(email);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(userStore.values());
    }

    @Override
    public Customer findCustomerById(Long id) {
        return idStore.get(id);
    }


}
