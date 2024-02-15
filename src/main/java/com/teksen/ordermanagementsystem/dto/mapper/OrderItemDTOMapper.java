package com.teksen.ordermanagementsystem.dto.mapper;

import com.teksen.ordermanagementsystem.dto.OrderItemDTO;
import com.teksen.ordermanagementsystem.model.OrderItem;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class OrderItemDTOMapper implements Function<OrderItem, OrderItemDTO> {

    @Override
    public OrderItemDTO apply(OrderItem orderItem) {
        return new OrderItemDTO(
                orderItem.getOrderItemId(),
                orderItem.getQuantity(),
                orderItem.getProduct().getProductId()
        );
    }
}
