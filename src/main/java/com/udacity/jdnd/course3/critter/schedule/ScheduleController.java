package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.services.EmployeeService;
import com.udacity.jdnd.course3.critter.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private EmployeeService employeeService;

    private ScheduleDTO getScheduleDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(schedule.getId());
        List<Long> employeeIds = schedule.getEmployees().stream().map(Employee::getId).collect(Collectors.toList());
        scheduleDTO.setEmployeeIds(employeeIds);
        List<Long> petIds = schedule.getPets().stream().map(Pet::getId).collect(Collectors.toList());
        scheduleDTO.setPetIds(petIds);
        scheduleDTO.setDate(schedule.getDate());
        scheduleDTO.setActivities(schedule.getActivities());
        return scheduleDTO;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule newSchedule = new Schedule();
        newSchedule.setDate(scheduleDTO.getDate());
        newSchedule.setActivities(scheduleDTO.getActivities());
        List<Long> employeeIds = scheduleDTO.getEmployeeIds();
        List<Long> petIds = scheduleDTO.getPetIds();
        return getScheduleDTO(scheduleService.saveSchedule(newSchedule, employeeIds, petIds));
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        List<ScheduleDTO> scheduleDTOs = schedules.stream().map(this::getScheduleDTO).collect(Collectors.toList());
        return scheduleDTOs;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> schedules = scheduleService.getAllSchedulesByPet(petId);
        List<ScheduleDTO> scheduleDTOs = schedules.stream().map(this::getScheduleDTO).collect(Collectors.toList());
        return scheduleDTOs;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> schedules = scheduleService.getAllSchedulesByEmployee(employeeId);
        List<ScheduleDTO> scheduleDTOs = schedules.stream().map(this::getScheduleDTO).collect(Collectors.toList());
        return scheduleDTOs;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> schedules = scheduleService.getAllSchedulesByCustomer(customerId);
        List<ScheduleDTO> scheduleDTOs = schedules.stream().map(this::getScheduleDTO).collect(Collectors.toList());
        return scheduleDTOs;
    }
}
