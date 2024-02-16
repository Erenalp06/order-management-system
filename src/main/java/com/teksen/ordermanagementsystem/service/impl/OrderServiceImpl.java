package com.teksen.ordermanagementsystem.service.impl;

import com.teksen.ordermanagementsystem.exception.custom.OrderNotFoundException;
import com.teksen.ordermanagementsystem.model.Customer;
import com.teksen.ordermanagementsystem.model.Order;
import com.teksen.ordermanagementsystem.repository.OrderRepository;
import com.teksen.ordermanagementsystem.service.CustomerService;
import com.teksen.ordermanagementsystem.service.OrderService;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final Logger logger = org.slf4j.LoggerFactory.getLogger(OrderServiceImpl.class);

    public OrderServiceImpl(OrderRepository orderRepository, CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
    }

    @Override
    public List<Order> getAllOrders() {
        logger.info("Getting all orders");
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long orderId) {
        logger.info("Getting order by id: {}", orderId);
        return orderRepository.findById(orderId).orElseThrow(
                () -> new OrderNotFoundException("Order with id: " + orderId + " not found"));
    }
    @Override
    public Order createOrder(Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(new Date(System.currentTimeMillis()));

        try{
            return orderRepository.save(order);
        } catch (Exception ex) {
            logger.error("An unexpected error occurred while creating order: {}", order, ex);
            throw new RuntimeException("An unexpected error occurred");
        }
    }

    @Override
    public Order updateOrder(Long orderId, Order toUpdateOrder) {
        Order order = this.getOrderById(orderId);
        order.setOrderDate(toUpdateOrder.getOrderDate());
        order.setCustomer(toUpdateOrder.getCustomer());


        try {
            return orderRepository.save(order);
        } catch (Exception ex) {
            logger.error("An unexpected error occurred while updating order: {}", order, ex);
            throw new RuntimeException("An unexpected error occurred");
        }
    }

    @Override
    public Boolean deleteOrderById(Long customerId, Long orderId) {
        Order order = this.getOrderByCustomerId(orderId, customerId);

        if(orderRepository.existsById(order.getOrderId())){
            try{
                orderRepository.deleteById(order.getOrderId());
                logger.info("Order with id: {} deleted successfully", order.getOrderId());
            } catch (Exception ex) {
                logger.error("An unexpected error occurred while deleting order: {}", order, ex);
                throw new RuntimeException("An unexpected error occurred");
            }
            return true;
        }
        return false;
    }

    @Override
    public List<Order> getOrdersByCustomerId(Long customerId) {
        logger.info("Getting all orders by customer id: {}", customerId);
        Customer customer = customerService.getCustomerById(customerId);
        return orderRepository.findByCustomer_CustomerId(customer.getCustomerId());
    }

    @Override
    public Order getOrderByCustomerId(Long orderId, Long customerId) {
        logger.info("Getting order by id: {} for customer with id: {}", orderId, customerId);
        Customer customer = customerService.getCustomerById(customerId);
        return orderRepository.findOrderByOrderIdAndCustomerCustomerId(orderId, customer.getCustomerId())
                .orElseThrow(() -> new OrderNotFoundException("Order with id: " + orderId + " not found for customer with id: " + customerId));
    }


}
