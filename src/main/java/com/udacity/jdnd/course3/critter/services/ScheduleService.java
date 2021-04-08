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

    public List<Schedule> getAllSchedules() {
        return schedulesRepository.findAll();
    }

    public List<Schedule> getAllSchedulesByPet(Long petId) {
        return schedulesRepository.getAllSchedulesByPetIds(petId);
    }

    public List<Schedule> getAllSchedulesByEmployee(Long employeeIds) {
        return schedulesRepository.getAllSchedulesByEmployeeIds(employeeIds);
    }

    public List<Schedule> getAllSchedulesByCustomer(Long customerId) {
        return schedulesRepository.getAllSchedulesByCustomerId(customerId);
    }

    public Schedule saveSchedule(Schedule schedule) {
        return schedulesRepository.save(schedule);
    }

}
