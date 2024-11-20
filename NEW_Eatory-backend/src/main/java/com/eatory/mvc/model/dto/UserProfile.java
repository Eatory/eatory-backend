package com.eatory.mvc.model.dto;

import java.util.List;

public class UserProfile {
	private String username;
	private String profileImage;
	private int height;
	private int weight;
	private String allergyName;
	private int postCount;
	private int followerCount;
	private int followeeCount;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getAllergyName() {
		return allergyName;
	}
	public void setAllergyName(String allergyName) {
		this.allergyName = allergyName;
	}
	public int getPostCount() {
		return postCount;
	}
	public void setPostCount(int postCount) {
		this.postCount = postCount;
	}
	public int getFollowerCount() {
		return followerCount;
	}
	public void setFollowerCount(int followerCount) {
		this.followerCount = followerCount;
	}
	public int getFolloweeCount() {
		return followeeCount;
	}
	public void setFolloweeCount(int followingCount) {
		this.followeeCount = followeeCount;
	}
	public Object getAllergies() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setAllergies(List<String> allergies) {
		// TODO Auto-generated method stub
		
	}
	
	

}
