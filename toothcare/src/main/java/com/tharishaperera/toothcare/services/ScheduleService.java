package com.tharishaperera.toothcare.services;

import com.tharishaperera.toothcare.models.Schedule;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {

    // get all schedules
    public List<Schedule> getAllSchedules() {
        return Schedule.schedules;
    }

    // create schedule
    public Schedule createSchedule(Schedule schedule) {
        Schedule.schedules.add(schedule);
        return schedule;
    }

    // get schedule by id
    public Optional<Schedule> getScheduleById(long scheduleId) {
        return Schedule.schedules.stream()
                .filter(schedule -> schedule.getScheduleId() == scheduleId)
                .findFirst();
    }

    // update schedule by id
    public void updateScheduleById(long scheduleId, Schedule updatedSchedule) {
        Optional<Schedule> scheduleToUpdate = Schedule.schedules.stream()
                .filter(schedule -> schedule.getScheduleId() == scheduleId)
                .findFirst();

        scheduleToUpdate.ifPresent(schedule -> {
            schedule.setDentist(updatedSchedule.getDentist());
            schedule.setDay(updatedSchedule.getDay());
            schedule.setStartTime(updatedSchedule.getStartTime());
            schedule.setEndTime(updatedSchedule.getEndTime());
        });
    }

    // delete schedule by id
    public void deleteScheduleById(long scheduleId) {
        Schedule.schedules.removeIf(schedule -> schedule.getScheduleId() == scheduleId);
    }
}
