package com.eatory.model.dao;

import java.util.List;
import com.eatory.model.dto.Food;

public interface FoodDao {
    List<Food> findByRecordId(Long recordId);
    Food findById(Long foodId);
    int insert(Food food);
    int update(Food food);
    int delete(Long foodId);
}
