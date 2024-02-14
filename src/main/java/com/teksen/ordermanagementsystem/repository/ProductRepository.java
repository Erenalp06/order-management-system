package com.teksen.ordermanagementsystem.repository;

import com.teksen.ordermanagementsystem.model.OrderItem;
import com.teksen.ordermanagementsystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> deleteProductByProductId(Long productId);

}
