package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.repositories.SchedulesRepository;
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

    private ScheduleDTO getScheduleDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(schedule.getId());
        scheduleDTO.setEmployeeIds(schedule.getEmployeeIds());
        scheduleDTO.setPetIds(schedule.getPetIds());
        scheduleDTO.setDate(schedule.getDate());
        scheduleDTO.setActivities(schedule.getActivities());
        return scheduleDTO;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule newSchedule = new Schedule();
        newSchedule.setDate(scheduleDTO.getDate());
        newSchedule.setActivities(scheduleDTO.getActivities());
        newSchedule.setEmployeeIds(scheduleDTO.getEmployeeIds());
        newSchedule.setPetIds(scheduleDTO.getPetIds());
        return getScheduleDTO(scheduleService.saveSchedule(newSchedule));
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
