package com.teksen.ordermanagementsystem.service.impl;

import com.teksen.ordermanagementsystem.dto.OrderItemDTO;
import com.teksen.ordermanagementsystem.exception.custom.OrderItemNotFoundException;
import com.teksen.ordermanagementsystem.exception.custom.OrderNotFoundException;
import com.teksen.ordermanagementsystem.model.Order;
import com.teksen.ordermanagementsystem.model.OrderItem;
import com.teksen.ordermanagementsystem.model.Product;
import com.teksen.ordermanagementsystem.repository.OrderItemRepository;
import com.teksen.ordermanagementsystem.service.OrderItemService;
import com.teksen.ordermanagementsystem.service.OrderService;
import com.teksen.ordermanagementsystem.service.ProductService;
import org.slf4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderService orderService;
    private final ProductService productService;
    private final Logger logger = org.slf4j.LoggerFactory.getLogger(OrderItemServiceImpl.class);

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, OrderService orderService, ProductService productService) {
        this.orderItemRepository = orderItemRepository;
        this.orderService = orderService;
        this.productService = productService;
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        logger.info("Getting all order items");
        return orderItemRepository.findAll();
    }

    @Override
    public OrderItem getOrderItemById(Long orderItemId) {
        logger.info("Getting order item by id: {}", orderItemId);
        return orderItemRepository.findById(orderItemId).orElseThrow(
                () -> new OrderItemNotFoundException("Order item with id: " + orderItemId + " not found")
        );
    }

    @Override
    public OrderItem createOrderItem(Long customerId, Long orderId, OrderItemDTO orderItemDTO) {
        Order order = orderService.getOrderByCustomerId(orderId, customerId);
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderItemId(orderItemDTO.orderItemId());
        orderItem.setQuantity(orderItemDTO.quantity());
        orderItem.setOrder(order);

        Product product = productService.getProductById(orderItemDTO.productId());
        orderItem.setProduct(product);

        try{
            return orderItemRepository.save(orderItem);
        } catch (Exception e) {
            logger.error("An unexpected error occurred while creating order item: {}", orderItem, e);
            throw new RuntimeException("Failed to create order item", e);
        }
    }

    @Override
    public OrderItem updateOrderItem(Long customerId, Long orderId,OrderItemDTO orderItemDTO, Long orderItemId) {
        Order order = orderService.getOrderByCustomerId(orderId, customerId);
        OrderItem orderItem = orderItemRepository.findById(orderItemId).orElseThrow(
                () -> new OrderNotFoundException("Order item with id: " + orderItemId + " not found")
        );

        if(!order.equals(orderItem.getOrder())){
            throw new RuntimeException("");
        }

        Product product = productService.getProductById(orderItemDTO.productId());
        orderItem.setProduct(product);
        orderItem.setQuantity(orderItemDTO.quantity());

        try {
            return orderItemRepository.save(orderItem);
        } catch (Exception e) {
            logger.error("An unexpected error occurred while updating order item: {}", orderItem, e);
            throw new RuntimeException("Failed to update order item", e);
        }
    }

    @Override
    public Boolean deleteOrderItemById(Long customerId, Long orderId, Long orderItemId) {

        Order order = orderService.getOrderByCustomerId(orderId, customerId);
        OrderItem orderItem = orderItemRepository.findById(orderItemId).orElseThrow(
                () -> new OrderNotFoundException("Order item with id: " + orderItemId + " not found")
        );

        if(!order.equals(orderItem.getOrder())){
            throw new RuntimeException("");
        }

        if(orderItemRepository.existsById(orderItemId)){
            try {
                orderItemRepository.deleteById(orderItemId);
                logger.info("Deleting order item by id: {}", orderItemId);
            } catch (DataAccessException e) {
                logger.error("An unexpected error occurred while deleting order item: {}", orderItem, e);
                throw new RuntimeException(e);
            }
            return true;
        }
        return false;
    }

    @Override
    public List<OrderItem> getAllOrderItemsByOrderId(Long customerId, Long orderId) {
        logger.info("Getting all order items by order id: {}", orderId);
        Order order = orderService.getOrderByCustomerId(orderId, customerId);
        return orderItemRepository.findByOrder_OrderId(order.getOrderId());
    }
}
