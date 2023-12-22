package com.tharishaperera.toothcare.models;

import com.tharishaperera.toothcare.utils.Utils;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Schedule {
    public static final List<Schedule> schedules = new ArrayList<>();

    private long scheduleId;
    private Dentist dentist;
    private String day;
    private LocalTime startTime;
    private LocalTime endTime;

    public Schedule(long scheduleId, Dentist dentist, String day, LocalTime startTime, LocalTime endTime) {
        this.scheduleId = scheduleId;
        this.dentist = dentist;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public long getScheduleId() {
        return scheduleId;
    }
    public void setScheduleId(long scheduleId) {
        this.scheduleId = scheduleId;
    }
    public Dentist getDentist() {
        return dentist;
    }
    public void setDentist(Dentist dentist) {
        this.dentist = dentist;
    }
    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }
    public LocalTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
    public LocalTime getEndTime() {
        return endTime;
    }
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    // crate schedule
    public static Schedule createSchedule(Dentist dentist, String day, LocalTime startTime, LocalTime endTime) {
        long scheduleId = Utils.generateId();
        return new Schedule(scheduleId, dentist, day, startTime, endTime);
    }
}
