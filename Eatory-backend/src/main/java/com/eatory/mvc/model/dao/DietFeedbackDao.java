package com.eatory.mvc.model.dao;

import java.util.List;

import com.eatory.mvc.model.dto.DietFeedback;

public interface DietFeedbackDao {
    DietFeedback findByRecordId(Long recordId);
    int insert(DietFeedback dietFeedback);
    int update(DietFeedback dietFeedback);
    int delete(Long feedbackId);
}
