package com.sparta.schedule.service;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }


    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        // RequestDto -> Entity
        Schedule schedule = new Schedule(requestDto);

        // DB 저장
        Schedule saveSchedule = scheduleRepository.save(schedule);

        // Entity -> ResponseDto
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);

        return scheduleResponseDto;


    }

    public List<ScheduleResponseDto> getAllSchedules() {
        // DB 조회
        return scheduleRepository.findAll();
    }

    public ScheduleResponseDto getSchedule(Long id) {

        // 해당 일정이 DB에 존재하는지 확인
        Schedule schedule = scheduleRepository.findById(id);

        if (schedule != null) {
            return new ScheduleResponseDto(schedule);
        } else {
            throw new IllegalArgumentException("선택한 일정이 존재하지 않습니다.");
        }
    }
}
