package com.eatory.mvc.model.service;

import java.util.List;

import com.eatory.mvc.model.dto.DietRecord;

public interface DietRecordService {
    List<DietRecord> getRecordsByUserId(Long userId);
    DietRecord getRecordById(Long recordId);
    DietRecord addDietRecord(DietRecord dietRecord);
    DietRecord updateDietRecord(DietRecord dietRecord);
    boolean deleteDietRecord(Long recordId);
}
