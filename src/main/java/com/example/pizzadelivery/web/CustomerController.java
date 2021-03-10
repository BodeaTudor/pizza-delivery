package com.example.pizzadelivery.web;

import com.example.pizzadelivery.domain.Customer;
import com.example.pizzadelivery.service.CustomerService;
import com.example.pizzadelivery.transfer.customer.SaveCustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody @Valid SaveCustomerRequest request) {

        Customer newCustomer = customerService.createCustomer(request);

        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }
}
