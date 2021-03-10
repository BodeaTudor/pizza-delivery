package com.example.pizzadelivery.persistence;

import com.example.pizzadelivery.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
