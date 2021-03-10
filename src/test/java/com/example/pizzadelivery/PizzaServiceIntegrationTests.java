package com.example.pizzadelivery;

import com.example.pizzadelivery.domain.Pizza;
import com.example.pizzadelivery.exceptions.ResourceNotFoundException;
import com.example.pizzadelivery.service.PizzaService;
import com.example.pizzadelivery.transfer.pizza.SavePizzaRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PizzaServiceIntegrationTests {

    @Autowired
    private PizzaService pizzaService;

    @Test
    public void testAddPizza_whenValidRequest_thenReturnCreatedPizza() {

        addPizza();

    }

    @Test
    public void testRetrievePizza_whenExistingEntity_thenReturnPizza() {

        Pizza createdPizza = addPizza();

        Pizza retrievedPizza = pizzaService.retrievePizza(createdPizza.getId());
        assertThat(retrievedPizza, notNullValue());
        assertThat(retrievedPizza.getId(), is(createdPizza.getId()));
        assertThat(retrievedPizza.getName(), is(createdPizza.getName()));
        assertThat(retrievedPizza.getIngredients(), is(createdPizza.getIngredients()));
        assertThat(retrievedPizza.getPrice(), is(createdPizza.getPrice()));
        assertThat(retrievedPizza.getImagePath(), is(createdPizza.getImagePath()));

    }

    @Test(expected = ResourceNotFoundException.class)
    public void testRetrievePizza_whenNonExistingEntity_thenThrowException() {

        pizzaService.retrievePizza(9999);
    }

    @Test
    public void testUpdatePizza_whenValidRequest_thenReturnUpdatedPizza() {

        Pizza createdPizza = addPizza();

        SavePizzaRequest request = new SavePizzaRequest();
        request.setName(createdPizza.getName() + " UPDATED");
        request.setIngredients(createdPizza.getIngredients() + " UPDATED");
        request.setPrice(createdPizza.getPrice() + 20);
        request.setImagePath(createdPizza.getImagePath() + " UPDATED");

        Pizza updatedPizza = pizzaService.updatePizza(createdPizza.getId(), request);
        assertThat(updatedPizza, notNullValue());
        assertThat(updatedPizza.getId(), is(createdPizza.getId()));
        assertThat(updatedPizza.getName(), is(request.getName()));
        assertThat(updatedPizza.getPrice(), is(request.getPrice()));
        assertThat(updatedPizza.getIngredients(), is(request.getIngredients()));
        assertThat(updatedPizza.getImagePath(), is(request.getImagePath()));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testUpdatePizza_whenInvalidRequest_thenThrowException() {

        SavePizzaRequest request = new SavePizzaRequest();

        pizzaService.updatePizza(900, request);
    }

    @Test
    public void testDeletePizza_whenMultipleExistingPizzas_thenDeleteTheRightPizza() {

        Pizza createdPizza = addPizza();
        Pizza createdPizza2 = addPizza();

        pizzaService.deletePizza(createdPizza.getId());
        Pizza pizzaInDb = pizzaService.retrievePizza(createdPizza2.getId());
        assertThat(pizzaInDb, notNullValue());
        assertThat(pizzaInDb.getId(), is(createdPizza2.getId()));
        assertThat(pizzaInDb.getName(), is(createdPizza2.getName()));
        assertThat(pizzaInDb.getPrice(), is(createdPizza2.getPrice()));
        assertThat(pizzaInDb.getIngredients(), is(createdPizza2.getIngredients()));
        assertThat(pizzaInDb.getImagePath(), is(createdPizza2.getImagePath()));

    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testDeletePizza_whenNonExistingPizza_thenThrowException() {

        pizzaService.deletePizza(9999);
    }

    public Pizza addPizza() {

        SavePizzaRequest request = new SavePizzaRequest();
        request.setName("Test Pizza");
        request.setIngredients("Test Ingredients");
        request.setPrice(21.2);
        request.setImagePath("Image Path for Test Pizza");

        Pizza pizza = pizzaService.addPizza(request);
        assertThat(pizza, notNullValue());
        assertThat(pizza.getId(), notNullValue());
        assertThat(pizza.getId(), greaterThan((0L)));
        assertThat(pizza.getName(), is(request.getName()));
        assertThat(pizza.getPrice(), is(request.getPrice()));
        assertThat(pizza.getIngredients(), is(request.getIngredients()));
        assertThat(pizza.getImagePath(), is(request.getImagePath()));

        return pizza;
    }
}
