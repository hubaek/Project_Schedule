package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {

    private Long id;
    private String schedule;
    private String name;
    private LocalDateTime in_dtm;
    private LocalDateTime up_dtm;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.schedule = schedule.getSchedule();
        this.name = schedule.getName();
        this.in_dtm = schedule.getIn_dtm();
        this.up_dtm = schedule.getUp_dtm();
    }

    public ScheduleResponseDto(Long id, String schedule, String name, LocalDateTime inDtm, LocalDateTime upDtm) {
        this.id = id;
        this.schedule = schedule;
        this.name = name;
        this.in_dtm = inDtm;
        this.up_dtm = upDtm;
    }
}
