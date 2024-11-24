package com.eatory.mvc.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DietRecord {
    private Long recordId;
    private Long userId;
    @JsonFormat(pattern = "yyyy-MM-dd") // 응답시 yyyy-MM-dd 형태로 변환
    private String recordDate; // 변경: Date → String
    private String mealType;
    private List<String> menus;
    private String notes;

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

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
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
    
    @Override
    public String toString() {
        return "DietRecord{" +
                "recordId=" + recordId +
                ", userId=" + userId +
                ", recordDate=" + recordDate +
                ", mealType='" + mealType + '\'' +
                ", menus=" + menus +
                ", notes='" + notes + '\'' +
                '}';
    }
}
