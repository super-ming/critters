package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.repositories.PetsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PetService {
    @Autowired
    private PetsRepository petsRepository;

    public List<Pet> getAllPets() {
        return petsRepository.findAll();
    }

    public List<Pet> getAllPetsByCustomer(Long customerId) {
        return petsRepository.getAllPetsByCustomerId(customerId);
    }

    public Pet getPetById(Long id) {
        return petsRepository.getOne(id);
    }

    public Pet savePet(Pet pet) {
        return petsRepository.save(pet);
    }
}
