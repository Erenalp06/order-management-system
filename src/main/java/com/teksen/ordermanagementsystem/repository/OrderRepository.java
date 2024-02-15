package com.teksen.ordermanagementsystem.repository;

import com.teksen.ordermanagementsystem.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomer_CustomerId(Long customerId);

    Optional<Order> deleteOrderByOrderId(Long orderId);

    Optional<Order> findOrderByOrderIdAndCustomerCustomerId(Long orderId, Long customerId);
}
