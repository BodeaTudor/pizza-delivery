package com.example.pizzadelivery.web;

import com.example.pizzadelivery.service.CartService;
import com.example.pizzadelivery.transfer.cart.AddPizzaToCartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PutMapping
    public ResponseEntity addPizzaToCart(@RequestBody @Valid AddPizzaToCartRequest request) {

        cartService.addPizzaToCart(request);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

