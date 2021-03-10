package com.example.pizzadelivery.web;

import com.example.pizzadelivery.domain.Pizza;
import com.example.pizzadelivery.service.PizzaService;
import com.example.pizzadelivery.transfer.pizza.SavePizzaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {

    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @PostMapping
    public ResponseEntity<Pizza> addPizza(@RequestBody @Valid SavePizzaRequest request) {

        Pizza newPizza = pizzaService.addPizza(request);

        return new ResponseEntity<>(newPizza, HttpStatus.CREATED);
    }
}
