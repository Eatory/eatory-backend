package com.eatory.mvc.model.service;

import java.util.Optional;

import com.eatory.mvc.model.dto.DietFeedback;

public interface DietFeedbackService {
    Optional<DietFeedback> getFeedbackByRecordId(Long recordId);
    DietFeedback addDietFeedback(DietFeedback dietFeedback);
    DietFeedback updateDietFeedback(DietFeedback dietFeedback);
    boolean deleteDietFeedback(Long feedbackId);
}
