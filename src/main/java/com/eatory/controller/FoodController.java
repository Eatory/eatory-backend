package com.eatory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eatory.model.dto.Food;
import com.eatory.model.service.FoodService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/food")
@Tag(name = "Food API", description = "Food CRUD API")
public class FoodController {

    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    // 특정 기록 ID에 속한 음식 조회
    @GetMapping("/record/{recordId}")
    @Operation(summary = "음식 조회", description = "특정 기록에 속한 모든 음식을 조회합니다.")
    public ResponseEntity<List<Food>> getFoodsByRecordId(@PathVariable("recordId") Long recordId) {
        List<Food> foodList = foodService.getFoodsByRecordId(recordId);
        if (foodList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(foodList);
    }

    // 특정 ID의 음식 조회
    @GetMapping("/{foodId}")
    @Operation(summary = "특정 음식 조회", description = "특정 ID의 음식을 조회합니다.")
    public ResponseEntity<Food> getFoodById(@PathVariable("foodId") Long foodId) {
        Food food = foodService.getFoodById(foodId);
        if (food == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(food);
    }

    // 새로운 음식 추가
    @PostMapping("/")
    @Operation(summary = "음식 추가", description = "새로운 음식을 추가합니다.")
    public ResponseEntity<Food> addFood(@RequestBody Food food) {
        Food createdFood = foodService.addFood(food);
        return new ResponseEntity<>(createdFood, HttpStatus.CREATED);
    }

    // 음식 수정
    @PutMapping("/{foodId}")
    @Operation(summary = "음식 수정", description = "특정 ID의 음식을 수정합니다.")
    public ResponseEntity<Food> updateFood(@PathVariable("foodId") Long foodId, @RequestBody Food food) {
        food.setFoodId(foodId);
        Food updatedFood = foodService.updateFood(food);
        if (updatedFood == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updatedFood);
    }

    // 음식 삭제
    @DeleteMapping("/{foodId}")
    @Operation(summary = "음식 삭제", description = "특정 ID의 음식을 삭제합니다.")
    public ResponseEntity<Void> deleteFood(@PathVariable("foodId") Long foodId) {
        boolean isDeleted = foodService.deleteFood(foodId);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
