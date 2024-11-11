package com.eatory.model.dao;

import java.util.List;
import com.eatory.model.dto.DietRecord;

public interface DietRecordDao {
    List<DietRecord> findByUserId(Long userId);
    DietRecord findById(Long recordId);
    int insert(DietRecord dietRecord);
    int update(DietRecord dietRecord);
    int delete(Long recordId);
}
