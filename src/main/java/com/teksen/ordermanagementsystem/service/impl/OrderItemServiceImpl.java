package com.teksen.ordermanagementsystem.service.impl;

import com.teksen.ordermanagementsystem.dto.OrderItemDTO;
import com.teksen.ordermanagementsystem.model.Order;
import com.teksen.ordermanagementsystem.model.OrderItem;
import com.teksen.ordermanagementsystem.model.Product;
import com.teksen.ordermanagementsystem.repository.OrderItemRepository;
import com.teksen.ordermanagementsystem.service.OrderItemService;
import com.teksen.ordermanagementsystem.service.OrderService;
import com.teksen.ordermanagementsystem.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderService orderService;
    private final ProductService productService;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, OrderService orderService, ProductService productService) {
        this.orderItemRepository = orderItemRepository;
        this.orderService = orderService;
        this.productService = productService;
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    @Override
    public OrderItem getOrderItemById(Long orderItemId) {
        return orderItemRepository.findById(orderItemId).orElseThrow(
                () -> new RuntimeException("order item not found!")
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

        return orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItem updateOrderItem(Long customerId, Long orderId,OrderItemDTO orderItemDTO, Long orderItemId) {
        Order order = orderService.getOrderByCustomerId(orderId, customerId);
        OrderItem orderItem = orderItemRepository.findById(orderItemId).orElseThrow(
                () -> new RuntimeException("order item not found")
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
            throw new RuntimeException("Failed to update order item", e);
        }
    }

    @Override
    public Boolean deleteOrderItemById(Long customerId, Long orderId, Long orderItemId) {

        Order order = orderService.getOrderByCustomerId(orderId, customerId);
        OrderItem orderItem = orderItemRepository.findById(orderItemId).orElseThrow(
                () -> new RuntimeException("order item not found")
        );

        if(!order.equals(orderItem.getOrder())){
            throw new RuntimeException("");
        }

        if(orderItemRepository.existsById(orderItemId)){
            try {
                orderItemRepository.deleteById(orderItemId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        return false;
    }

    @Override
    public List<OrderItem> getAllOrderItemsByOrderId(Long customerId, Long orderId) {
        Order order = orderService.getOrderByCustomerId(orderId, customerId);
        return orderItemRepository.findByOrder_OrderId(order.getOrderId());
    }
}
