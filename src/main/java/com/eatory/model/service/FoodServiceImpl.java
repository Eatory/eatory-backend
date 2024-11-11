package com.eatory.model.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eatory.model.dao.FoodDao;
import com.eatory.model.dto.Food;

@Service
public class FoodServiceImpl implements FoodService {

    private final FoodDao foodDao;

    @Autowired
    public FoodServiceImpl(FoodDao foodDao) {
        this.foodDao = foodDao;
    }

    @Override
    public List<Food> getFoodsByRecordId(Long recordId) {
        return foodDao.findByRecordId(recordId);
    }

    @Override
    public Food getFoodById(Long foodId) {
        return foodDao.findById(foodId);
    }

    @Override
    public Food addFood(Food food) {
        foodDao.insert(food);
        return food;
    }

    @Override
    public Food updateFood(Food food) {
        foodDao.update(food);
        return food;
    }

    @Override
    public boolean deleteFood(Long foodId) {
        return foodDao.delete(foodId) > 0;
    }
}
