package com.example.pizzadelivery.web;

import com.example.pizzadelivery.domain.Pizza;
import com.example.pizzadelivery.service.PizzaService;
import com.example.pizzadelivery.transfer.pizza.SavePizzaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
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

    @GetMapping()
    public ResponseEntity<Page<Pizza>> retrievePizzas(Pageable pageable) {

        Page<Pizza> pizzas = pizzaService.retrievePizzas(pageable);

        return new ResponseEntity<>(pizzas, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pizza> updatePizza(@PathVariable("id") long id, @RequestBody @Valid SavePizzaRequest request) {

        Pizza updatedPizza = pizzaService.updatePizza(id, request);

        return new ResponseEntity<>(updatedPizza, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePizza(@PathVariable("id") long id) {

        pizzaService.deletePizza(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
