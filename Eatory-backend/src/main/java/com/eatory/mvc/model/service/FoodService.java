package com.eatory.mvc.model.service;

import java.util.List;

import com.eatory.mvc.model.dto.Food;

public interface FoodService {
    List<Food> getFoodsByRecordId(Long recordId);
    Food getFoodById(Long foodId);
    Food addFood(Food food);
    Food updateFood(Food food);
    boolean deleteFood(Long foodId);
}
