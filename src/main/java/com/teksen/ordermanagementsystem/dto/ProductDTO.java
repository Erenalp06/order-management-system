package com.teksen.ordermanagementsystem.dto;

public record ProductDTO(
        Long productId,
        String productName,
        double price
) {
}
