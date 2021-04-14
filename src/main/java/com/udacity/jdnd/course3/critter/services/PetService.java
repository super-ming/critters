package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.repositories.CustomersRepository;
import com.udacity.jdnd.course3.critter.repositories.PetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    @Autowired
    private PetsRepository petsRepository;

    @Autowired
    private CustomersRepository customersRepository;

    public List<Pet> getAllPets() {
        return petsRepository.findAll();
    }

    public List<Pet> getAllPetsByCustomer(Long ownerId) {
        Customer owner = customersRepository.getOne(ownerId);
        return owner.getPets();
    }

    public Pet getPetById(Long id) {
        return petsRepository.getOne(id);
    }

    public Pet savePet(Pet pet) {
        return petsRepository.save(pet);
    }
}
