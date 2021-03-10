package com.example.pizzadelivery.transfer.cart;

import com.example.pizzadelivery.transfer.pizza.PizzaInCartResponse;
import lombok.Data;

import java.util.Set;

@Data
public class CartResponse {

    private long id;

    private Set<PizzaInCartResponse> pizzas;
}
