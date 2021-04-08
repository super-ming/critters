package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.repositories.CustomersRepository;
import com.udacity.jdnd.course3.critter.repositories.EmployeesRepository;
import com.udacity.jdnd.course3.critter.repositories.PetsRepository;
import com.udacity.jdnd.course3.critter.repositories.SchedulesRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ScheduleService {
    @Autowired
    private SchedulesRepository schedulesRepository;

    @Autowired
    private PetsRepository petsRepository;

    @Autowired
    private EmployeesRepository employeesRepository;

    @Autowired
    private CustomersRepository customersRepository;

    public List<Schedule> getAllSchedules() {
        return schedulesRepository.findAll();
    }

    public List<Schedule> getAllSchedulesForPet(Long petId) {
        return schedulesRepository.getAllSchedulesByPetIds(petId);
    }

    public List<Schedule> getAllSchedulesForEmployee(Long employeeIds) {
        return schedulesRepository.getAllSchedulesByEmployeeIds(employeeIds);
    }

    public List<Schedule> getAllSchedulesForCustomer(Long customerId) {
        return schedulesRepository.getAllSchedulesByCustomerId(customerId);
    }

    public Schedule saveSchedule(Schedule schedule, List<Long> employeeIds, List<Long> petIds) {
        schedule.setEmployees(employeeIds);
        schedule.setPets(petIds);
        return schedulesRepository.save(schedule);
    }

}
