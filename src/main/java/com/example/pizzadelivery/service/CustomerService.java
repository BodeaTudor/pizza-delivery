package com.example.pizzadelivery.service;

import com.example.pizzadelivery.domain.Customer;
import com.example.pizzadelivery.domain.Pizza;
import com.example.pizzadelivery.exceptions.ResourceNotFoundException;
import com.example.pizzadelivery.persistence.CustomerRepository;
import com.example.pizzadelivery.transfer.customer.SaveCustomerRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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

    public Customer getCustomer(long id) {

        LOGGER.info("Retrieving customer with id: {}", id);

        return customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer with id " + id + " not found."));
    }

    public Customer updateCustomer(long id, SaveCustomerRequest request) {

        Customer customer = getCustomer(id);

        LOGGER.info("Updating customer with id {}: {}", id, request);

        BeanUtils.copyProperties(request, customer);

        return customerRepository.save(customer);
    }

    public void deleteCustomer(long id) {

        getCustomer(id);

        LOGGER.info("Deleting customer with id: {}", id);

        customerRepository.deleteById(id);
    }
}
