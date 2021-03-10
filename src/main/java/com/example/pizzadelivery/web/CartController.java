package com.example.pizzadelivery.web;

import com.example.pizzadelivery.service.CartService;
import com.example.pizzadelivery.transfer.cart.AddPizzaToCartRequest;
import com.example.pizzadelivery.transfer.cart.CartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
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

    @GetMapping("/{id}")
    public ResponseEntity<CartResponse> getCart(@PathVariable("id") long customerId) {

        CartResponse cart = cartService.getCart(customerId);

        return new ResponseEntity<>(cart, HttpStatus.OK);

    }

    @DeleteMapping
    public ResponseEntity removePizzaFromCart(@RequestBody @Valid AddPizzaToCartRequest request) {

        cartService.removePizzaFromCart(request);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCart(@PathVariable("id") long id) {

        cartService.deleteCart(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

