package com.eatory.model.dto;

import java.util.Date;

public class Calendar {
    private Integer calendarId;  // 일정 ID
    private Long userId;         // 사용자 ID
    private Date calendar_date;           // 날짜
    private Float dietScore;     // 식단 점수
    private String notes;        // 메모

    // 기본 생성자
    public Calendar() {
    }

    // 모든 필드를 포함한 생성자
    public Calendar(Integer calendarId, Long userId, Date calendar_date, Float dietScore, String notes) {
        this.calendarId = calendarId;
        this.userId = userId;
        this.calendar_date = calendar_date;
        this.dietScore = dietScore;
        this.notes = notes;
    }

    // Getter 및 Setter 메서드
    public Integer getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(Integer calendarId) {
        this.calendarId = calendarId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCalendarDate() {
        return calendar_date;
    }

    public void setCalendarDate(Date calendar_date) {
        this.calendar_date = calendar_date;
    }

    public Float getDietScore() {
        return dietScore;
    }

    public void setDietScore(Float dietScore) {
        this.dietScore = dietScore;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // toString 메서드
    @Override
    public String toString() {
        return "CalendarDto{" +
                "calendarId=" + calendarId +
                ", userId=" + userId +
                ", calendar_date=" + calendar_date +
                ", dietScore=" + dietScore +
                ", notes='" + notes + '\'' +
                '}';
    }
}