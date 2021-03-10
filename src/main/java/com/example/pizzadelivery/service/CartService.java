package com.example.pizzadelivery.service;

import com.example.pizzadelivery.domain.Cart;
import com.example.pizzadelivery.domain.Customer;
import com.example.pizzadelivery.domain.Pizza;
import com.example.pizzadelivery.exceptions.ResourceNotFoundException;
import com.example.pizzadelivery.persistence.CartRepository;
import com.example.pizzadelivery.transfer.cart.AddPizzaToCartRequest;
import com.example.pizzadelivery.transfer.cart.CartResponse;
import com.example.pizzadelivery.transfer.pizza.PizzaInCartResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class CartService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);

    private final CartRepository cartRepository;
    private final CustomerService customerService;
    private final PizzaService pizzaService;

    @Autowired
    public CartService(CartRepository cartRepository, CustomerService customerService, PizzaService pizzaService) {
        this.cartRepository = cartRepository;
        this.customerService = customerService;
        this.pizzaService = pizzaService;
    }

    @Transactional
    public void addPizzaToCart(AddPizzaToCartRequest request) {

        LOGGER.info("Adding product to cart: {}", request);

        Cart cart = cartRepository.findById(request.getCustomerId()).orElse(new Cart());

        if (cart.getCustomer() == null) {

            LOGGER.info("Cart doesn't exist. Retrieving customer to create a new cart.");
            Customer customer = customerService.getCustomer(request.getCustomerId());
            cart.setCustomer(customer);
        }

        Pizza pizza = pizzaService.retrievePizza(request.getPizzaId());
        cart.addToCart(pizza);

        cartRepository.save(cart);
    }

    @Transactional
    public CartResponse getCart(long customerId) {
        LOGGER.info("Retrieving cart from customer with id: {}", customerId);
        Cart cart = cartRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException(
                "There is no cart for customer with id: " + customerId));

        CartResponse cartResponse = new CartResponse();
        cartResponse.setId(cart.getId());

        Set<PizzaInCartResponse> pizzas = new HashSet<>();

        for (Pizza pizza : cart.getPizzas()) {
            PizzaInCartResponse pizzaInCartResponse = new PizzaInCartResponse();
            pizzaInCartResponse.setId(pizza.getId());
            pizzaInCartResponse.setName(pizza.getName());
            pizzaInCartResponse.setPrice(pizza.getPrice());
            pizzaInCartResponse.setIngredients(pizza.getIngredients());
            pizzaInCartResponse.setImagePath(pizza.getImagePath());

            pizzas.add(pizzaInCartResponse);
        }

        cartResponse.setPizzas(pizzas);

        return cartResponse;
    }
}
