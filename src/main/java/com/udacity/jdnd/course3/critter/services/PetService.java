package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.repositories.CustomersRepository;
import com.udacity.jdnd.course3.critter.repositories.PetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PetService {
    @Autowired
    private PetsRepository petsRepository;

    @Autowired
    private CustomersRepository customersRepository;

    @Transactional
    public List<Pet> getAllPets() {
        return petsRepository.findAll();
    }

    @Transactional
    public List<Pet> getAllPetsByOwner(Long ownerId) {
        Customer owner = customersRepository.getOne(ownerId);
        return owner.getPets();
    }

    @Transactional
    public Pet getPetById(Long id) {
        return petsRepository.getOne(id);
    }

    @Transactional
    public Pet savePet(Pet pet, Customer owner) {
        Pet savedPet = petsRepository.saveAndFlush(pet);
        Customer ownerInDB = customersRepository.findById(owner.getId()).get();
        ownerInDB.addPet(savedPet);
        customersRepository.saveAndFlush(ownerInDB);
        return savedPet;
    }
}
