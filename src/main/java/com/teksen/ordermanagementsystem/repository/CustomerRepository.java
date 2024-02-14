package com.teksen.ordermanagementsystem.repository;

import com.teksen.ordermanagementsystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByEmail(String email);

    List<Customer> findByFirstName(String firstName);

    Optional<Customer> deleteCustomerByCustomerId(Long customerId);
}
