package com.example.pizzadelivery.service;

import com.example.pizzadelivery.domain.Pizza;
import com.example.pizzadelivery.exceptions.ResourceNotFoundException;
import com.example.pizzadelivery.persistence.PizzaRepository;
import com.example.pizzadelivery.transfer.pizza.SavePizzaRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(Pizza.class);

    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public Pizza addPizza(SavePizzaRequest request) {

        LOGGER.info("Adding new pizza: {}", request);

        Pizza pizza = new Pizza();
        pizza.setName(request.getName());
        pizza.setIngredients(request.getIngredients());
        pizza.setPrice(request.getPrice());
        pizza.setImagePath(request.getImagePath());

        return pizzaRepository.save(pizza);
    }

    public Pizza retrievePizza(long id) {

        LOGGER.info("Retrieving pizza with id: {}", id);

        return pizzaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pizza with id " + id + " not found."));
    }

    public Pizza updatePizza(long id, SavePizzaRequest request) {

        Pizza pizza = retrievePizza(id);

        LOGGER.info("Updating pizza with id {}: {}", id, request);

        BeanUtils.copyProperties(request, pizza);

        return pizzaRepository.save(pizza);
    }

    public void deletePizza(long id) {

        retrievePizza(id);

        LOGGER.info("Deleting pizza with id: {}", id);

        pizzaRepository.deleteById(id);
    }
}
