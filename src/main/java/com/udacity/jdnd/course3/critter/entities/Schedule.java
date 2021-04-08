package com.udacity.jdnd.course3.critter.entities;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Schedule {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDate date;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Employee.class)
    private List<Long> employeeIds;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Pet.class)
    private List<Long> petIds;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Customer.class)
    private List<Long> customerId;

    @ElementCollection
    private Set<EmployeeSkill> activities;

    public List<Long> getEmployees() {
        return employeeIds;
    }

    public void setEmployees(List<Long> employeeIds) {
        this.employeeIds = employeeIds;
    }

    public List<Long> getPets() {
        return petIds;
    }

    public void setPets(List<Long> petIds) {
        this.petIds = petIds;
    }
}
