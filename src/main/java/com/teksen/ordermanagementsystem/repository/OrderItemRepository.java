package com.teksen.ordermanagementsystem.repository;

import com.teksen.ordermanagementsystem.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findByOrder_OrderId(Long orderId);

    Optional<OrderItem> deleteOrderItemByOrderItemId(Long orderItemId);
}
