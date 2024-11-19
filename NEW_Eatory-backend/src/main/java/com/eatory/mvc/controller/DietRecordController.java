package com.eatory.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eatory.mvc.model.dto.DietRecord;
import com.eatory.mvc.model.service.DietRecordService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/dietRecord")
@Tag(name = "DietRecord API", description = "DietRecord CRUD API")
@CrossOrigin("*")
public class DietRecordController {

    private final DietRecordService dietRecordService;

    @Autowired
    public DietRecordController(DietRecordService dietRecordService) {
        this.dietRecordService = dietRecordService;
    }

    // 특정 사용자 ID에 속한 기록 조회
    @GetMapping("/user/{userId}")
    @Operation(summary = "기록 조회", description = "특정 사용자에 속한 모든 기록을 조회합니다.")
    public ResponseEntity<List<DietRecord>> getRecordsByUserId(@PathVariable("userId") Long userId) {
        List<DietRecord> dietRecords = dietRecordService.getRecordsByUserId(userId);
        if (dietRecords.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(dietRecords);
    }

    // 특정 ID의 기록 조회
    @GetMapping("/{recordId}")
    @Operation(summary = "특정 기록 조회", description = "특정 ID의 기록을 조회합니다.")
    public ResponseEntity<DietRecord> getRecordById(@PathVariable("recordId") Long recordId) {
        DietRecord dietRecord = dietRecordService.getRecordById(recordId);
        if (dietRecord == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(dietRecord);
    }

    // 새로운 기록 추가
    @PostMapping("/")
    @Operation(summary = "기록 추가", description = "새로운 기록을 추가합니다.")
    public ResponseEntity<DietRecord> addDietRecord(@RequestBody DietRecord dietRecord) {
        DietRecord createdDietRecord = dietRecordService.addDietRecord(dietRecord);
        return new ResponseEntity<>(createdDietRecord, HttpStatus.CREATED);
    }

    // 기록 수정
    @PutMapping("/{recordId}")
    @Operation(summary = "기록 수정", description = "특정 ID의 기록을 수정합니다.")
    public ResponseEntity<DietRecord> updateDietRecord(@PathVariable("recordId") Long recordId, @RequestBody DietRecord dietRecord) {
        dietRecord.setRecordId(recordId);
        DietRecord updatedDietRecord = dietRecordService.updateDietRecord(dietRecord);
        if (updatedDietRecord == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updatedDietRecord);
    }

    // 기록 삭제
    @DeleteMapping("/{recordId}")
    @Operation(summary = "기록 삭제", description = "특정 ID의 기록을 삭제합니다.")
    public ResponseEntity<Void> deleteDietRecord(@PathVariable("recordId") Long recordId) {
        boolean isDeleted = dietRecordService.deleteDietRecord(recordId);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
