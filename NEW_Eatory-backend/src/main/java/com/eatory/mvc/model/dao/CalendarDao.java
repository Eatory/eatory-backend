package com.eatory.mvc.model.dao;

import java.util.List;

import com.eatory.mvc.model.dto.Calendar;

public interface CalendarDao {
	List<Calendar> findByUserId(Long userId);
	Calendar findByUserIdAndDate(Long userId, String date);
    int insert(Calendar calendar);
    int update(Calendar calendar);
    int delete(int calendarId);
}
