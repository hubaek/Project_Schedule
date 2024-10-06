package com.sparta.schedule.repository;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Schedule save(Schedule schedule) {
        // DB 저장
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환 받기 위한 객체

        String sql = "INSERT INTO schedule (schedule, name, password) VALUES(?, ?, ?)";
        jdbcTemplate.update(con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, schedule.getSchedule());
                    preparedStatement.setString(2, schedule.getName());
                    preparedStatement.setString(3, schedule.getPassword());
                    return preparedStatement;
                },
                keyHolder);

        Long id = keyHolder.getKey().longValue();
        schedule.setId(id);

        return schedule;
    }

    public List<ScheduleResponseDto> findAll() {

        // DB 조회
        String sql = "SELECT * FROM schedule";

        return jdbcTemplate.query(sql, new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                // SQL 의 결과로 받아온 Schedule 데이터들을 ScheduleResponseDto 타입으로 변환해줄 메서드
                Long id = rs.getLong("id");
                String schedule = rs.getString("schedule");
                String name = rs.getString("name");
                LocalDateTime in_dtm = rs.getTimestamp("in_dtm").toLocalDateTime();
                LocalDateTime up_dtm = rs.getTimestamp("up_dtm").toLocalDateTime();
                return new ScheduleResponseDto(id, schedule, name, in_dtm, up_dtm);
            }
        });
    }

    public Schedule findById(Long id) {
        String sql = "SELECT * FROM schedule WHERE id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                Schedule schedule = new Schedule();
                schedule.setSchedule(resultSet.getString("schedule"));
                schedule.setName(resultSet.getString("name"));
                schedule.setIn_dtm(resultSet.getTimestamp("in_dtm").toLocalDateTime());
                schedule.setUp_dtm(resultSet.getTimestamp("up_dtm").toLocalDateTime());
                return schedule;
            } else {
                return null;
            }
        }, id);
    }

    public void update(Long id, ScheduleRequestDto requestDto) {
        String sql = "UPDATE schedule SET schedule = ?, name = ?, up_dtm = ? WHERE id = ?";
        jdbcTemplate.update(sql, requestDto.getSchedule(), requestDto.getName(), requestDto.getUp_dtm(), id);
    }
}
