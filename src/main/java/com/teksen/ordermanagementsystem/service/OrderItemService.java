package com.teksen.ordermanagementsystem.service;


import com.teksen.ordermanagementsystem.model.OrderItem;

import java.util.List;

public interface OrderItemService {

    List<OrderItem> getAllOrderItems();
    OrderItem getOrderItemById(Long orderItemId);
    OrderItem createOrderItem(OrderItem toCreateOrderItem);
    OrderItem updateOrderItem(Long orderItemId, OrderItem toUpdateOrderItem);
    Boolean deleteOrderItemById(Long orderItemId);
    List<OrderItem> getAllOrderItemsByOrderId(Long orderId);


}
