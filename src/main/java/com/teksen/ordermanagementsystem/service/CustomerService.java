package com.teksen.ordermanagementsystem.service;


import com.teksen.ordermanagementsystem.model.Customer;
import com.teksen.ordermanagementsystem.model.Order;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();
    Customer getCustomerById(Long customerId);
    Customer createCustomer(Customer toCreateCustomer);
    Customer updateCustomer(Long customerId, Customer toUpdateCustomer);
    Boolean deleteCustomerById(Long customerId);

}
