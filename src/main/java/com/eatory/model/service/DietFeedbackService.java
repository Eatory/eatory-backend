package com.eatory.model.service;

import java.util.Optional;
import com.eatory.model.dto.DietFeedback;

public interface DietFeedbackService {
    Optional<DietFeedback> getFeedbackByRecordId(Long recordId);
    DietFeedback addDietFeedback(DietFeedback dietFeedback);
    DietFeedback updateDietFeedback(DietFeedback dietFeedback);
    boolean deleteDietFeedback(Long feedbackId);
}
