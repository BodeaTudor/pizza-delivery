package com.example.pizzadelivery.transfer.cart;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddPizzaToCartRequest {

    @NotNull
    private long customerId;
    @NotNull
    private long pizzaId;
}
