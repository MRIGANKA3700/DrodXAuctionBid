package com.example.ee.ejb.remote;

import com.example.ee.core.model.Customer;
import jakarta.ejb.Local;
import jakarta.ejb.Remote;

import java.util.List;

@Local
@Remote
public interface CustomerService {
    boolean registerCustomer(Customer customer);
    Customer login(String usernameOrEmail, String password);
    boolean isUsernameTaken(String username);
    boolean isEmailTaken(String email);
    List<Customer> getAllCustomers();
    Customer findCustomerById(Long id);
}