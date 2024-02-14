package com.teksen.ordermanagementsystem.controller;

import com.teksen.ordermanagementsystem.model.Customer;
import com.teksen.ordermanagementsystem.response.ResponseHandler;
import com.teksen.ordermanagementsystem.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> findAll(){
        return customerService.getAllCustomers();
    }

    @GetMapping("{customerId}")
    public ResponseEntity<Object> findCustomerById(@PathVariable Long customerId){
        return ResponseHandler.responseBuilder(
                "Customer information has been successfully retrieved",
                HttpStatus.OK,
                customerService.getCustomerById(customerId)
        );
    }

    @PostMapping
    public ResponseEntity<Object> addCustomer(@RequestBody Customer customer){
        return ResponseHandler.responseBuilder(
                "Customer has been successfully created",
                HttpStatus.CREATED,
                customerService.createCustomer(customer)
        );
    }


}
