package com.eatory.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eatory.mvc.model.dto.Calendar;
import com.eatory.mvc.model.service.CalendarService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api-calendar")
@Tag(name="Calendar API", description="Calender CRUD API")
@CrossOrigin("*")
public class CalendarController {
	private final CalendarService calendarService;
	
	@Autowired
	public CalendarController(CalendarService calendarService) {
		this.calendarService = calendarService;
	}
	
	// 캘린더 조회 (특정 사용자의 모든 날짜 기록) - 얘는 데이터를 주는디 . . . -> 화면에 한달 보이는건 뷰의 몫?
    @GetMapping("/{userId}")
    @Operation(summary = "캘린더 조회", description = "특정 사용자의 모든 날짜 기록을 조회합니다.")
    public ResponseEntity<List<Calendar>> getUserCalendar(@PathVariable("userId") Long userId) {
        List<Calendar> calendarList = calendarService.getUserCalendar(userId);
        if (calendarList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(calendarList);
    }

    // 특정 날짜 기록 조회
    @GetMapping("/{userId}/{date}")
    @Operation(summary = "특정 날짜 기록 조회", description = "특정 사용자의 특정 날짜의 기록을 조회합니다.")
    public ResponseEntity<Calendar> getCalendarByDate(@PathVariable("userId") Long userId, @PathVariable("date") String date) {
        Calendar calendar = calendarService.getCalendarByDate(userId, date);
        if (calendar == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(calendar);
    }

    // 새로운 날짜 기록 생성
    @PostMapping("/")
    @Operation(summary = "날짜 기록 생성", description = "특정 날짜의 새로운 캘린더 기록을 생성합니다.")
    public ResponseEntity<Calendar> addCalendarRecord(@RequestBody Calendar calendar) {
        Calendar createdCalendar = calendarService.addCalendarRecord(calendar);
        return new ResponseEntity<>(createdCalendar, HttpStatus.CREATED);
    }

    // 날짜 기록 수정
    @PutMapping("/{calendarId}")
    @Operation(summary = "날짜 기록 수정", description = "특정 날짜의 캘린더 기록을 수정합니다.")
    public ResponseEntity<Calendar> updateCalendarRecord(@PathVariable("calendarId") int calendarId, @RequestBody Calendar calendar) {
        calendar.setCalendarId(calendarId);
        Calendar updatedCalendar = calendarService.updateCalendarRecord(calendar);
        if (updatedCalendar == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updatedCalendar);
    }

    // 날짜 기록 삭제
    @DeleteMapping("/{calendarId}")
    @Operation(summary = "날짜 기록 삭제", description = "특정 날짜의 캘린더 기록을 삭제합니다.")
    public ResponseEntity<Void> deleteCalendarRecord(@PathVariable("calendarId") int calendarId) {
        boolean isDeleted = calendarService.deleteCalendarRecord(calendarId);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
