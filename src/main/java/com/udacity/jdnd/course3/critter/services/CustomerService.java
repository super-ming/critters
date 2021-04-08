package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomerService {
    @Autowired
    private CustomersRepository customersRepository;

    public List<Customer> getAllCustomers() {
        return customersRepository.findAll();
    }

    public Customer getCustomerByPetId(Long petId) {
        return customersRepository.getCustomerByPetId(petId);
    }

    public Customer saveCustomer(Customer customerId) {
        return customersRepository.save(customerId);
    }
}
