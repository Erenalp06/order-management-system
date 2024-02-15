package com.teksen.ordermanagementsystem.dto;

public record CustomerDTO(
        Long customerId,
        String firstName,
        String lastName,
        String email
) {

}
