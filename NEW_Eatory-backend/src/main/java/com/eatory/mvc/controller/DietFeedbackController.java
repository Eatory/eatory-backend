package com.eatory.mvc.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eatory.mvc.model.dto.DietFeedback;
import com.eatory.mvc.model.service.DietFeedbackService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api-feedback")
@Tag(name = "DietFeedback API", description = "Diet Feedback CRUD API")
@CrossOrigin("*")
public class DietFeedbackController {
    private final DietFeedbackService dietFeedbackService;

    @Autowired
    public DietFeedbackController(DietFeedbackService dietFeedbackService) {
        this.dietFeedbackService = dietFeedbackService;
    }

    // 특정 기록에 대한 피드백 조회
    @GetMapping("/{recordId}")
    @Operation(summary = "특정 기록에 대한 피드백 조회", description = "특정 기록의 피드백을 조회합니다.")
    public ResponseEntity<DietFeedback> getFeedbackByRecordId(@PathVariable("recordId") Long recordId) {
        Optional<DietFeedback> dietFeedback = dietFeedbackService.getFeedbackByRecordId(recordId);
        return dietFeedback.map(ResponseEntity::ok)
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // 피드백 추가
    @PostMapping("/")
    @Operation(summary = "피드백 추가", description = "새로운 피드백을 추가합니다.")
    public ResponseEntity<DietFeedback> addDietFeedback(@RequestBody DietFeedback dietFeedback) {
        DietFeedback createdFeedback = dietFeedbackService.addDietFeedback(dietFeedback);
        return new ResponseEntity<>(createdFeedback, HttpStatus.CREATED);
    }

    // 피드백 수정
    @PutMapping("/{feedbackId}")
    @Operation(summary = "피드백 수정", description = "특정 피드백을 수정합니다.")
    public ResponseEntity<DietFeedback> updateDietFeedback(@PathVariable("feedbackId") Long feedbackId, 
                                                           @RequestBody DietFeedback dietFeedback) {
        dietFeedback.setFeedbackId(feedbackId);
        DietFeedback updatedFeedback = dietFeedbackService.updateDietFeedback(dietFeedback);
        return ResponseEntity.ok(updatedFeedback);
    }

    // 피드백 삭제
    @DeleteMapping("/{feedbackId}")
    @Operation(summary = "피드백 삭제", description = "특정 피드백을 삭제합니다.")
    public ResponseEntity<Void> deleteDietFeedback(@PathVariable("feedbackId") Long feedbackId) {
        boolean isDeleted = dietFeedbackService.deleteDietFeedback(feedbackId);
        return isDeleted ? ResponseEntity.status(HttpStatus.OK).build()
                         : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
