package com.eatory.mvc.model.dto;

public class UserAllergyRequest {
    private Long userId;
    private Long allergyId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAllergyId() {
        return allergyId;
    }

    public void setAllergyId(Long allergyId) {
        this.allergyId = allergyId;
    }

    @Override
    public String toString() {
        return "UserAllergyRequest [userId=" + userId + ", allergyId=" + allergyId + "]";
    }
}

