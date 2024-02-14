package com.teksen.ordermanagementsystem.service.impl;

import com.teksen.ordermanagementsystem.model.Order;
import com.teksen.ordermanagementsystem.model.OrderItem;
import com.teksen.ordermanagementsystem.repository.OrderItemRepository;
import com.teksen.ordermanagementsystem.service.OrderItemService;
import com.teksen.ordermanagementsystem.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderService orderService;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, OrderService orderService) {
        this.orderItemRepository = orderItemRepository;
        this.orderService = orderService;
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
    public OrderItem createOrderItem(OrderItem toCreateOrderItem) {
        return orderItemRepository.save(toCreateOrderItem);
    }

    @Override
    public OrderItem updateOrderItem(Long orderItemId, OrderItem toUpdateOrderItem) {
        OrderItem orderItem = this.getOrderItemById(orderItemId);

        orderItem.setProduct(toUpdateOrderItem.getProduct());
        orderItem.setOrder(toUpdateOrderItem.getOrder());
        orderItem.setQuantity(toUpdateOrderItem.getQuantity());

        try {
            return orderItemRepository.save(orderItem);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update order item", e);
        }
        //TODO Look at this point
    }

    @Override
    public Boolean deleteOrderItemById(Long orderItemId) {
        Optional<OrderItem> orderItem = orderItemRepository.deleteOrderItemByOrderItemId(orderItemId);
        return orderItem.isPresent();
    }

    @Override
    public List<OrderItem> getAllOrderItemsByOrderId(Long orderId) {
        Order order = orderService.getOrderById(orderId);
        return orderItemRepository.findByOrder_OrderId(order.getOrderId());
    }
}
