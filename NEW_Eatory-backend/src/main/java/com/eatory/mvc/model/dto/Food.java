package com.eatory.mvc.model.dto;

public class Food {
    private Long foodId;
    private Long recordId;
    private String foodName;
    private Float calories;
    private Float fat;
    private Float protein;
    private Float carbohydrates;
    private Float sugar;
    private Float cholesterol;
    private Float sodium;
	public Long getFoodId() {
		return foodId;
	}
	public void setFoodId(Long foodId) {
		this.foodId = foodId;
	}
	public Long getRecordId() {
		return recordId;
	}
	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public Float getCalories() {
		return calories;
	}
	public void setCalories(Float calories) {
		this.calories = calories;
	}
	public Float getFat() {
		return fat;
	}
	public void setFat(Float fat) {
		this.fat = fat;
	}
	public Float getProtein() {
		return protein;
	}
	public void setProtein(Float protein) {
		this.protein = protein;
	}
	public Float getCarbohydrates() {
		return carbohydrates;
	}
	public void setCarbohydrates(Float carbohydrates) {
		this.carbohydrates = carbohydrates;
	}
	public Float getSugar() {
		return sugar;
	}
	public void setSugar(Float sugar) {
		this.sugar = sugar;
	}
	public Float getCholesterol() {
		return cholesterol;
	}
	public void setCholesterol(Float cholesterol) {
		this.cholesterol = cholesterol;
	}
	public Float getSodium() {
		return sodium;
	}
	public void setSodium(Float sodium) {
		this.sodium = sodium;
	}
    
}
