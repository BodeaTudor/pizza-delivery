package com.example.pizzadelivery.persistence;

import com.example.pizzadelivery.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
