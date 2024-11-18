package com.eatory.mvc.model.dao;

import java.util.List;

import com.eatory.mvc.model.dto.Food;

public interface FoodDao {
    List<Food> findByRecordId(Long recordId);
    Food findById(Long foodId);
    int insert(Food food);
    int update(Food food);
    int delete(Long foodId);
}
