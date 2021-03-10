package com.example.pizzadelivery.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Cart {

    @Id
    private long id;

    @OneToOne
    @MapsId
    private Customer customer;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "cart_pizzas",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "pizza_id"))
    private Set<Pizza> pizzas = new HashSet<>();

    public void addToCart(Pizza pizza) {

        pizzas.add(pizza);

        pizza.getCarts().add(this);
    }

    public void removeFromCart(Pizza pizza) {

        pizzas.remove(pizza);

        pizza.getCarts().remove(this);
    }
}
