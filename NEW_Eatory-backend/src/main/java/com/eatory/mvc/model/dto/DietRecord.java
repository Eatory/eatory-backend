package com.eatory.mvc.model.dto;

import java.util.Date;
import java.util.List;

public class DietRecord {
    private Long recordId;
    private Long userId;
    private Date recordDate;
    private String mealType; // 아침, 점심, 저녁, 간식
    private List<String> menus; // JSON 문자열 대신 List<String>으로 메뉴를 관리
    private String notes; // 간단한 메모

    // Getter와 Setter
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

    public List<String> getMenus() {
        return menus;
    }

    public void setMenus(List<String> menus) {
        this.menus = menus;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
