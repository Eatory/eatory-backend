package com.eatory.model.service;

import java.util.List;

import com.eatory.model.dto.Calendar;

public interface CalendarService {
	List<Calendar> getUserCalendar(Long userId);
    Calendar getCalendarByDate(Long userId, String date);
    Calendar addCalendarRecord(Calendar calendar);
    Calendar updateCalendarRecord(Calendar calendar);
    boolean deleteCalendarRecord(int calendarId);
}
