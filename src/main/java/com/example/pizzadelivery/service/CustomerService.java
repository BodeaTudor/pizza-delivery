package com.example.pizzadelivery.service;

import com.example.pizzadelivery.domain.Customer;
import com.example.pizzadelivery.domain.Pizza;
import com.example.pizzadelivery.persistence.CustomerRepository;
import com.example.pizzadelivery.transfer.customer.SaveCustomerRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(Pizza.class);

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(SaveCustomerRequest request) {

        LOGGER.info("Creating customer: {}", request);

        Customer customer = new Customer();
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());

        return customerRepository.save(customer);
    }
}
