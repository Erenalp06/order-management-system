package com.teksen.ordermanagementsystem.controller;

import com.teksen.ordermanagementsystem.dto.OrderItemDTO;
import com.teksen.ordermanagementsystem.dto.mapper.OrderItemDTOMapper;
import com.teksen.ordermanagementsystem.model.Order;
import com.teksen.ordermanagementsystem.response.ResponseHandler;
import com.teksen.ordermanagementsystem.service.OrderItemService;
import com.teksen.ordermanagementsystem.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class OrderItemController {
    private final OrderItemService orderItemService;
    private final OrderItemDTOMapper orderItemDTOMapper;


    public OrderItemController(OrderItemService orderItemService, OrderItemDTOMapper orderItemDTOMapper) {
        this.orderItemService = orderItemService;
        this.orderItemDTOMapper = orderItemDTOMapper;
    }

    @GetMapping("{customerId}/orders/{orderId}/order-items")
    public ResponseEntity<Object> getCustomerOrderOrderItems(@PathVariable Long customerId, @PathVariable Long orderId){
        List<OrderItemDTO> orderItems = orderItemService.getAllOrderItemsByOrderId(customerId, orderId).stream()
                .map(orderItemDTOMapper)
                .collect(Collectors.toList());
        if (orderItems == null || orderItems.isEmpty()) {
            return ResponseHandler.responseBuilder(
                    "No order items found for order with ID " + orderId,
                    HttpStatus.NOT_FOUND,
                    null
            );
        }

        return ResponseHandler.responseBuilder(
                "Order items retrieved successfully",
                HttpStatus.OK,
                orderItems
        );
    }

    @GetMapping("{customerId}/orders/{orderId}/order-items/{orderItemId}")
    public ResponseEntity<Object> getCustomerOrderOneOrderItems(
            @PathVariable Long customerId,
            @PathVariable Long orderId,
            @PathVariable Long orderItemId
            ){
        OrderItemDTO orderItem = orderItemDTOMapper.apply(orderItemService.getOrderItemById(orderItemId));

        if (orderItem == null) {
            return ResponseHandler.responseBuilder(
                    "Order item with ID " + orderItemId + " not found",
                    HttpStatus.NOT_FOUND,
                    null
            );
        }

        return ResponseHandler.responseBuilder(
                "Order item retrieved successfully",
                HttpStatus.OK,
                orderItem
        );
    }

    @PostMapping("{customerId}/orders/{orderId}/order-items")
    public ResponseEntity<Object> addOrderItem(
            @PathVariable Long customerId,
            @PathVariable Long orderId,
            @RequestBody OrderItemDTO orderItemDTO
    ){
        return ResponseHandler.responseBuilder(
                "",
                HttpStatus.NOT_FOUND,
                orderItemDTOMapper.apply(orderItemService.createOrderItem(customerId, orderId, orderItemDTO))
        );
    }

    @PutMapping("{customerId}/orders/{orderId}/order-items/{orderItemId}")
    public ResponseEntity<Object> updateOrderItem(
            @PathVariable Long customerId,
            @PathVariable Long orderId,
            @PathVariable Long orderItemId,
            @RequestBody OrderItemDTO orderItemDTO
    ){
        return ResponseHandler.responseBuilder(
                "",
                HttpStatus.NOT_FOUND,
                orderItemDTOMapper.apply(orderItemService.updateOrderItem(customerId, orderId, orderItemDTO, orderItemId))
        );
    }

    @DeleteMapping("{customerId}/orders/{orderId}/order-items/{orderItemId}")
    public ResponseEntity<Object> deleteOrderItem(
            @PathVariable Long customerId,
            @PathVariable Long orderId,
            @PathVariable Long orderItemId
    ){
        if(Boolean.TRUE.equals(orderItemService.deleteOrderItemById(customerId, orderId, orderItemId))){
            return ResponseHandler.responseBuilder(
                    "Order item deleted successfully",
                    HttpStatus.OK,
                    null
            );
        }
        return ResponseHandler.responseBuilder(
                "Failed to delete order item",
                HttpStatus.BAD_REQUEST,
                null
        );

    }
}
