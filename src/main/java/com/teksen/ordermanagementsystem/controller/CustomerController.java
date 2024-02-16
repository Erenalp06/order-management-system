package com.teksen.ordermanagementsystem.controller;

import com.teksen.ordermanagementsystem.dto.CustomerDTO;
import com.teksen.ordermanagementsystem.dto.mapper.CustomerDTOMapper;
import com.teksen.ordermanagementsystem.model.Customer;
import com.teksen.ordermanagementsystem.response.ResponseHandler;
import com.teksen.ordermanagementsystem.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerDTOMapper customerDTOMapper;

    public CustomerController(CustomerService customerService, CustomerDTOMapper customerDTOMapper) {
        this.customerService = customerService;
        this.customerDTOMapper = customerDTOMapper;
    }

    @GetMapping
    public List<CustomerDTO> findAll(){
        return customerService.getAllCustomers().stream()
                .map(customerDTOMapper)
                .collect(Collectors.toList());
    }

    @GetMapping("{customerId}")
    public ResponseEntity<Object> findCustomerById(@PathVariable Long customerId){
        return ResponseHandler.responseBuilder(
                "Customer information has been successfully retrieved",
                HttpStatus.OK,
                customerDTOMapper.apply(customerService.getCustomerById(customerId))
        );
    }

    @PostMapping
    public ResponseEntity<Object> addCustomer(@Valid @RequestBody Customer customer){
        return ResponseHandler.responseBuilder(
                "Customer has been successfully created",
                HttpStatus.CREATED,
                customerDTOMapper.apply(customerService.createCustomer(customer))
        );
    }

    @PutMapping("{customerId}")
    public ResponseEntity<Object> updateCustomer(@PathVariable Long customerId, @RequestBody @Valid Customer customer){
        return ResponseHandler.responseBuilder(
                "Customer has been successfully updated",
                HttpStatus.OK,
                customerDTOMapper.apply(customerService.updateCustomer(customerId, customer))
        );
    }

    @DeleteMapping("{customerId}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable Long customerId){
        if(Boolean.TRUE.equals(customerService.deleteCustomerById(customerId))){
            return ResponseHandler.responseBuilder(
                    "Customer has been successfully deleted",
                    HttpStatus.OK,
                    null
            );
        }else{
            return ResponseHandler.responseBuilder(
                    "Customer could not be deleted",
                    HttpStatus.BAD_REQUEST,
                    null
            );
        }

    }


}
