package com.example.pizzadelivery.transfer.customer;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SaveCustomerRequest {

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
}
