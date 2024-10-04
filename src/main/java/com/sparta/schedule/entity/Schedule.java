package com.sparta.schedule.entity;

import com.sparta.schedule.dto.ScheduleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Schedule {

    private Long id;
    private String schedule;
    private String name;
    private String password;
    private LocalDateTime in_dtm;
    private LocalDateTime up_dtm;


    public Schedule(ScheduleRequestDto requestDto) {
        this.schedule = requestDto.getSchedule();
        this.name = requestDto.getName();
        this.password = requestDto.getPassword();
    }
}
