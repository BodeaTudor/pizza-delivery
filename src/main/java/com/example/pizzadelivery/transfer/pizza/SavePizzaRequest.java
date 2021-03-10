package com.example.pizzadelivery.transfer.pizza;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SavePizzaRequest {

    @NotNull
    private String name;
    @NotNull
    private String ingredients;
    @NotNull
    private double price;
    @NotNull
    private String imagePath;
}
