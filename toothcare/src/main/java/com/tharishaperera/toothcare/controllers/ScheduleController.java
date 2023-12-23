package com.tharishaperera.toothcare.controllers;

import com.tharishaperera.toothcare.models.Schedule;
import com.tharishaperera.toothcare.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/schedules")
@CrossOrigin(origins = "${frontend.allowed-origin}")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    // get all schedules
    @GetMapping()
    public List<Schedule> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }

    // get schedule by id
    @GetMapping("/{scheduleId}")
    public Optional<Schedule> getScheduleById(@PathVariable long scheduleId) {
        return scheduleService.getScheduleById(scheduleId);
    }

    // create schedule
    @PostMapping()
    public Schedule createSchedule(@RequestBody Schedule schedule) {
        Schedule newSchedule = Schedule.createSchedule(schedule.getDentist(), schedule.getDay(), schedule.getStartTime(), schedule.getEndTime());
        return scheduleService.createSchedule(newSchedule);
    }

    // update schedule
    @PutMapping("/{scheduleId}")
    public void updateSchedule(@PathVariable long scheduleId, @RequestBody Schedule updatedSchedule) {
        scheduleService.updateScheduleById(scheduleId, updatedSchedule);
    }

    // delete schedule by id
    @DeleteMapping("/{scheduleId}")
    public void deleteSchedule(@PathVariable long scheduleId) {
        scheduleService.deleteScheduleById(scheduleId);
    }
}
