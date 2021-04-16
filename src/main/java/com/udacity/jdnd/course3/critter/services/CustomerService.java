package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.repositories.CustomersRepository;
import com.udacity.jdnd.course3.critter.repositories.PetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private PetsRepository petsRepository;

    public List<Customer> getAllCustomers() {
        return customersRepository.findAll();
    }

    public Customer getOne(Long id) { return customersRepository.getOne(id); }

    public Customer getCustomerByPetId(Long petId) {
        return customersRepository.getOne(petsRepository.findById(petId).get().getOwner().getId());
    }

    @Transactional
    public Customer saveCustomer(Customer customer, List<Long> petIds) {
        List<Pet> pets = new ArrayList<>();
        if (petIds != null && !petIds.isEmpty()) {
            pets = petIds.stream().map((petId) -> petsRepository.getOne(petId)).collect(Collectors.toList());
        }
        customer.setPets(pets);
        return customersRepository.saveAndFlush(customer);
    }

    public void savePet(Long id, Pet pet){
        Customer owner = customersRepository.findById(id).get();
        pet.setOwner(owner);
        owner.addPet(pet);
        customersRepository.saveAndFlush(owner);
    }
}
