package com.teksen.ordermanagementsystem.dto.mapper;

import com.teksen.ordermanagementsystem.dto.CustomerDTO;
import com.teksen.ordermanagementsystem.model.Customer;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CustomerDTOMapper implements Function<Customer, CustomerDTO> {
    @Override
    public CustomerDTO apply(Customer customer) {
        return new CustomerDTO(
                customer.getCustomerId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail()
        );
    }
}
