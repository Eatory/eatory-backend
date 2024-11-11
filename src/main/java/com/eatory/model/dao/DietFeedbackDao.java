package com.eatory.model.dao;

import java.util.List;
import com.eatory.model.dto.DietFeedback;

public interface DietFeedbackDao {
    DietFeedback findByRecordId(Long recordId);
    int insert(DietFeedback dietFeedback);
    int update(DietFeedback dietFeedback);
    int delete(Long feedbackId);
}
