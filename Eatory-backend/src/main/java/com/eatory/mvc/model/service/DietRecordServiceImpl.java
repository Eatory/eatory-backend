package com.eatory.mvc.model.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eatory.mvc.model.dao.DietRecordDao;
import com.eatory.mvc.model.dto.DietRecord;

@Service
public class DietRecordServiceImpl implements DietRecordService {

    private final DietRecordDao dietRecordDao;

    @Autowired
    public DietRecordServiceImpl(DietRecordDao dietRecordDao) {
        this.dietRecordDao = dietRecordDao;
    }

    @Override
    public List<DietRecord> getRecordsByUserId(Long userId) {
        return dietRecordDao.findByUserId(userId);
    }

    @Override
    public DietRecord getRecordById(Long recordId) {
        return dietRecordDao.findById(recordId);
    }

    @Override
    public DietRecord addDietRecord(DietRecord dietRecord) {
        dietRecordDao.insert(dietRecord);
        return dietRecord;
    }

    @Override
    public DietRecord updateDietRecord(DietRecord dietRecord) {
        dietRecordDao.update(dietRecord);
        return dietRecord;
    }

    @Override
    public boolean deleteDietRecord(Long recordId) {
        return dietRecordDao.delete(recordId) > 0;
    }
}
