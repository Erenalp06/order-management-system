package com.teksen.ordermanagementsystem.dto;

import java.util.Date;

public record OrderDTO(
        Long orderId,
        Date orderDate,
        Long customerId
) {
}
