package com.teksen.ordermanagementsystem.dto.mapper;

import com.teksen.ordermanagementsystem.dto.OrderDTO;
import com.teksen.ordermanagementsystem.model.Order;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class OrderDTOMapper implements Function<Order, OrderDTO> {
    @Override
    public OrderDTO apply(Order order) {
        return new OrderDTO(
                order.getOrderId(),
                order.getOrderDate(),
                order.getCustomer().getCustomerId()

        );
    }
}
