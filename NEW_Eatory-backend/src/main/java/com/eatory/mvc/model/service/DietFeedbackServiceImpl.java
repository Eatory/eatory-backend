package com.eatory.mvc.model.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eatory.mvc.model.dao.DietFeedbackDao;
import com.eatory.mvc.model.dto.DietFeedback;

@Service
public class DietFeedbackServiceImpl implements DietFeedbackService {
    private final DietFeedbackDao dietFeedbackDao;

    @Autowired
    public DietFeedbackServiceImpl(DietFeedbackDao dietFeedbackDao) {
        this.dietFeedbackDao = dietFeedbackDao;
    }

    @Override
    public Optional<DietFeedback> getFeedbackByRecordId(Long recordId) {
        return Optional.ofNullable(dietFeedbackDao.findByRecordId(recordId));
    }

    @Override
    public DietFeedback addDietFeedback(DietFeedback dietFeedback) {
        dietFeedbackDao.insert(dietFeedback);
        return dietFeedback;
    }

    @Override
    public DietFeedback updateDietFeedback(DietFeedback dietFeedback) {
        dietFeedbackDao.update(dietFeedback);
        return dietFeedback;
    }

    @Override
    public boolean deleteDietFeedback(Long feedbackId) {
        return dietFeedbackDao.delete(feedbackId) > 0;
    }
}
