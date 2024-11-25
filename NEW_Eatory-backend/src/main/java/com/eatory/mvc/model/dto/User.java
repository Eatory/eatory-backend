package com.eatory.mvc.model.dto;

import java.time.LocalDate;

public class User {
	private Long userId;
	private String username;
	private String password;
	private String email;
	private long height;
	private long weight;
	private String gender;
	private LocalDate birthDate;
	private String profileImage;
	private String phoneNumber;
	
	
	

	public Long getUserId() {
		return userId;
	}




	public void setUserId(Long userId) {
		this.userId = userId;
	}




	public String getUsername() {
		return username;
	}




	public void setUsername(String username) {
		this.username = username;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public long getHeight() {
		return height;
	}




	public void setHeight(long height) {
		this.height = height;
	}




	public long getWeight() {
		return weight;
	}




	public void setWeight(long weight) {
		this.weight = weight;
	}




	public String getGender() {
		return gender;
	}




	public void setGender(String gender) {
		this.gender = gender;
	}




	public LocalDate getBirthDate() {
		return birthDate;
	}




	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}




	public String getProfileImage() {
		return profileImage;
	}




	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}




	public String getPhoneNumber() {
		return phoneNumber;
	}




	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}




	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", height=" + height + ", weight=" + weight + ", gender=" + gender + ", birthDate=" + birthDate
				+ ", profileImage=" + profileImage + ", phoneNumber=" + phoneNumber + "]";
	}
	
	
	

}
