package com.example.pizzadelivery.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Cart {

    @Id
    private long id;

    @OneToOne
    @MapsId
    private Customer customer;
}
