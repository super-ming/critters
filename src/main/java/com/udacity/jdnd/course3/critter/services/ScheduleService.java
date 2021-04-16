package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.repositories.CustomersRepository;
import com.udacity.jdnd.course3.critter.repositories.EmployeesRepository;
import com.udacity.jdnd.course3.critter.repositories.PetsRepository;
import com.udacity.jdnd.course3.critter.repositories.SchedulesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {
    @Autowired
    private SchedulesRepository schedulesRepository;

    @Autowired
    private EmployeesRepository employeesRepository;

    @Autowired
    private PetsRepository petsRepository;

    @Autowired
    private CustomersRepository customersRepository;

    @Transactional
    public List<Schedule> getAllSchedules() {
        return schedulesRepository.findAll();
    }

    @Transactional
    public List<Schedule> getAllSchedulesByPet(Long petId) {
        Pet pet = petsRepository.getOne(petId);
        return schedulesRepository.findAllByPetsContains(pet);
    }

    @Transactional
    public List<Schedule> getAllSchedulesByEmployee(Long employeeId) {
        Employee employee = employeesRepository.getOne(employeeId);
        return schedulesRepository.findAllByEmployeesContains(employee);
    }

    @Transactional
    public List<Schedule> getAllSchedulesByCustomer(Long customerId) {
        Customer customer = customersRepository.getOne(customerId);
        return schedulesRepository.findAllByPetsIn(customer.getPets());
    }

    @Transactional
    public Schedule saveSchedule(Schedule schedule, List<Long> employeeIds, List<Long> petIds) {
        List<Employee> employees = new ArrayList<>();
        if (employeeIds != null && !employeeIds.isEmpty()) {
            employees = employeeIds.stream().map((employeeId) -> employeesRepository.getOne(employeeId)).collect(Collectors.toList());
        }
        schedule.setEmployees(employees);
        List<Pet> pets = new ArrayList<>();
        if (petIds != null && !petIds.isEmpty()) {
            pets = petIds.stream().map((petId) -> petsRepository.getOne(petId)).collect(Collectors.toList());
        }
        schedule.setPets(pets);
        return schedulesRepository.saveAndFlush(schedule);
    }
}
