package com.eatory.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eatory.model.dao.CalendarDao;
import com.eatory.model.dto.Calendar;

@Service
public class CalendarServiceImpl implements CalendarService {
	
	private final CalendarDao calendarDao;
	
	public CalendarServiceImpl(CalendarDao calendarDao) {
		this.calendarDao = calendarDao;
	}

	@Override
	public List<Calendar> getUserCalendar(Long userId) {
		return calendarDao.findByUserId(userId);
	}

	@Override
	public Calendar getCalendarByDate(Long userId, String date) {
		return calendarDao.findByUserIdAndDate(userId, date);
	}

	@Override
	public Calendar addCalendarRecord(Calendar calendar) {
		calendarDao.insert(calendar);
		return calendar;
	}

	@Override
	public Calendar updateCalendarRecord(Calendar calendar) {
		int rowsUpdated = calendarDao.update(calendar);
		return rowsUpdated > 0 ? calendar : null;
	}

	@Override
	public boolean deleteCalendarRecord(int calendarId) {
		return calendarDao.delete(calendarId) > 0;
	}

}
