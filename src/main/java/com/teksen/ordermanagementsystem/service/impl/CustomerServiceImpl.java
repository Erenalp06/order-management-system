package com.teksen.ordermanagementsystem.service.impl;

import com.teksen.ordermanagementsystem.model.Customer;
import com.teksen.ordermanagementsystem.repository.CustomerRepository;
import com.teksen.ordermanagementsystem.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("customer not found!"));
    }

    @Override
    public Customer createCustomer(Customer toCreateCustomer) {
        return customerRepository.save(toCreateCustomer);
    }

    @Override
    public Customer updateCustomer(Long customerId, Customer toUpdateCustomer) {
        Customer customer = this.getCustomerById(customerId);
        customer.setEmail(toUpdateCustomer.getEmail());
        customer.setFirstName(toUpdateCustomer.getFirstName());
        customer.setLastName(toUpdateCustomer.getLastName());

        return customerRepository.save(customer);
    }

    @Override
    public Boolean deleteCustomerById(Long customerId) {
        return customerRepository.deleteCustomerByCustomerId(customerId).isPresent();
    }


}
