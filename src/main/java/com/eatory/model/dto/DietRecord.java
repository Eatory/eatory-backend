package com.eatory.model.dto;

import java.util.Date;

public class DietRecord {
    private Long recordId;
    private Long userId;
    private Integer calendarId;
    private Date recordDate;
    private String mealType;
    private String notes;
	public Long getRecordId() {
		return recordId;
	}
	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Integer getCalendarId() {
		return calendarId;
	}
	public void setCalendarId(Integer calendarId) {
		this.calendarId = calendarId;
	}
	public Date getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
	public String getMealType() {
		return mealType;
	}
	public void setMealType(String mealType) {
		this.mealType = mealType;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
}
