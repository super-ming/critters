package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchedulesRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> getAllSchedulesByPetIds(Long petIds);

    List<Schedule> getAllSchedulesByEmployeeIds(Long employeeIds);

    List<Schedule> getAllSchedulesByCustomerId(Long customerId);
}