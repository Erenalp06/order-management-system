package com.teksen.ordermanagementsystem.service.impl;

import com.teksen.ordermanagementsystem.model.Customer;
import com.teksen.ordermanagementsystem.model.Order;
import com.teksen.ordermanagementsystem.repository.OrderRepository;
import com.teksen.ordermanagementsystem.service.CustomerService;
import com.teksen.ordermanagementsystem.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;

    public OrderServiceImpl(OrderRepository orderRepository, CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(
                () -> new RuntimeException("order not found!")
        );
    }
    @Override
    public Order createOrder(Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(new Date(System.currentTimeMillis()));
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Long orderId, Order toUpdateOrder) {
        Order order = this.getOrderById(orderId);
        order.setOrderDate(toUpdateOrder.getOrderDate());
        order.setCustomer(toUpdateOrder.getCustomer());
        return orderRepository.save(order);
    }

    @Override
    public Boolean deleteOrderById(Long customerId, Long orderId) {
        Order order = orderRepository.findOrderByOrderIdAndCustomerCustomerId(orderId, customerId).orElseThrow(
                () -> new RuntimeException("order not found")
        );
        if(orderRepository.existsById(order.getOrderId())){
            orderRepository.deleteById(order.getOrderId());
            return true;
        }
        return false;
    }

    @Override
    public List<Order> getOrdersByCustomerId(Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        return orderRepository.findByCustomer_CustomerId(customer.getCustomerId());
    }

    @Override
    public Order getOrderByCustomerId(Long orderId, Long customerId) {
        return orderRepository.findOrderByOrderIdAndCustomerCustomerId(orderId, customerId).orElseThrow(
                () -> new RuntimeException("order not found!")
        );
    }


}
