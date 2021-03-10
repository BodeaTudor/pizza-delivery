package com.example.pizzadelivery.persistence;

import com.example.pizzadelivery.domain.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {

}
