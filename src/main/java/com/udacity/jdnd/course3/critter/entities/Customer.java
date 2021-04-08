package com.udacity.jdnd.course3.critter.entities;

import com.udacity.jdnd.course3.critter.entities.Pet;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String phoneNumber;
    private String notes;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Pet.class)
    private List<Long> petIds;
}
