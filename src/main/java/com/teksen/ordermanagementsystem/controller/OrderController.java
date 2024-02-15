package com.teksen.ordermanagementsystem.controller;

import com.teksen.ordermanagementsystem.dto.OrderDTO;
import com.teksen.ordermanagementsystem.dto.mapper.OrderDTOMapper;
import com.teksen.ordermanagementsystem.model.Order;
import com.teksen.ordermanagementsystem.response.ResponseHandler;
import com.teksen.ordermanagementsystem.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("customers/")
public class OrderController {
    private final OrderService orderService;
    private final OrderDTOMapper orderDTOMapper;

    public OrderController(OrderService orderService, OrderDTOMapper orderDTOMapper) {
        this.orderService = orderService;
        this.orderDTOMapper = orderDTOMapper;
    }

    @GetMapping("{customerId}/orders")
    public ResponseEntity<Object> getCustomerOrders(@PathVariable Long customerId){
        List<OrderDTO> orders = orderService.getOrdersByCustomerId(customerId).stream()
                .map(orderDTOMapper)
                .collect(Collectors.toList());

        if(orders.isEmpty()){
            return ResponseHandler.responseBuilder(
                    "Customer with ID " + customerId + " has no associated orders.",
                    HttpStatus.NOT_FOUND,
                    orders
            );
        }else{
            return ResponseHandler.responseBuilder(
                    "Orders associated with customer ID " + customerId + " retrieved successfully.",
                    HttpStatus.OK,
                    orders
            );
        }
    }

    @GetMapping("{customerId}/orders/{orderId}")
    public ResponseEntity<Object> getCustomerOrderById(@PathVariable Long customerId, @PathVariable Long orderId){
        OrderDTO order = orderDTOMapper.apply(orderService.getOrderByCustomerId(orderId, customerId));
        if (order == null){
            return ResponseHandler.responseBuilder(
                    "Order with ID " + orderId + " not found for customer ID " + customerId,
                    HttpStatus.NOT_FOUND,
                    null
            );
        }else{
            return ResponseHandler.responseBuilder(
                    "Order with ID " + orderId + " retrieved successfully.",
                    HttpStatus.OK,
                    order
            );
        }
    }

    @PostMapping("{customerId}/orders")
    public ResponseEntity<Object> addOrder(@PathVariable Long customerId){
        OrderDTO order = orderDTOMapper.apply(orderService.createOrder(customerId));
        if (order == null) {
            return ResponseHandler.responseBuilder(
                    "Error creating order",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }
        return ResponseHandler.responseBuilder(
                "Order created successfully",
                HttpStatus.CREATED,
                order
        );
    }

    @DeleteMapping("{customerId}/orders/{orderId}")
    public ResponseEntity<Object> deleteOrderById(@PathVariable Long customerId, @PathVariable Long orderId){
        if (Boolean.TRUE.equals(orderService.deleteOrderById(customerId, orderId))) {
            return ResponseHandler.responseBuilder(
                    "Order deleted successfully",
                    HttpStatus.OK,
                    null
            );
        } else {
            return ResponseHandler.responseBuilder(
                    "Order not found or access denied",
                    HttpStatus.NOT_FOUND,
                    null
            );
        }
    }




}
