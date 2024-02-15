package com.teksen.ordermanagementsystem.service;


import com.teksen.ordermanagementsystem.dto.OrderItemDTO;
import com.teksen.ordermanagementsystem.model.OrderItem;

import java.util.List;

public interface OrderItemService {

    List<OrderItem> getAllOrderItems();
    OrderItem getOrderItemById(Long orderItemId);
    OrderItem createOrderItem(Long customerId, Long orderId, OrderItemDTO orderItemDTO);
    OrderItem updateOrderItem(Long customerId, Long orderId,OrderItemDTO orderItemDTO, Long orderItemId);
    Boolean deleteOrderItemById(Long customerId, Long orderId, Long orderItemId);
    List<OrderItem> getAllOrderItemsByOrderId(Long customerId, Long orderId);


}
