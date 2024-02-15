package com.teksen.ordermanagementsystem.dto;

public record OrderItemDTO(
        Long orderItemId,
        int quantity,
        Long productId
) {
}
