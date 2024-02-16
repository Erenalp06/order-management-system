package com.teksen.ordermanagementsystem.service.impl;

import com.teksen.ordermanagementsystem.exception.custom.CustomerNotFoundException;
import com.teksen.ordermanagementsystem.model.Customer;
import com.teksen.ordermanagementsystem.repository.CustomerRepository;
import com.teksen.ordermanagementsystem.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        logger.info("Getting customer by id: {}", customerId);
        return customerRepository.findById(customerId).orElseThrow(
                () -> new CustomerNotFoundException("Customer with id: " + customerId + " not found"));
    }

    @Override
    public Customer createCustomer(Customer toCreateCustomer) {
        try {
            return customerRepository.save(toCreateCustomer);
        } catch (DataAccessException ex) {
            logger.error("An unexpected error occurred while creating customer: {}", toCreateCustomer, ex);
            throw new RuntimeException("An unexpected error occurred");
        }
    }

    @Override
    public Customer updateCustomer(Long customerId, Customer toUpdateCustomer) {
        Customer customer = this.getCustomerById(customerId);
        customer.setEmail(toUpdateCustomer.getEmail());
        customer.setFirstName(toUpdateCustomer.getFirstName());
        customer.setLastName(toUpdateCustomer.getLastName());

        try {
            return customerRepository.save(customer);
        } catch (DataAccessException ex) {
            logger.error("An unexpected error occurred while updating customer: {}", customer, ex);
            throw new RuntimeException("An unexpected error occurred");
        }
    }

    @Override
    @Transactional
    public Boolean deleteCustomerById(Long customerId) {
        this.getCustomerById(customerId);
        if (customerRepository.existsById(customerId)){
            logger.info("Deleting customer by id: {}", customerId);
            customerRepository.deleteCustomerByCustomerId(customerId);
            return true;
        }
        return false;
    }


}
