package com.teksen.ordermanagementsystem.service;

import com.teksen.ordermanagementsystem.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();
    Order getOrderById(Long orderId);
    Order createOrder(Order toCreateOrder);
    Order updateOrder(Long orderId, Order toUpdateOrder);
    Boolean deleteOrderById(Long orderId);
    List<Order> getOrdersByCustomerId(Long customerId);
}
