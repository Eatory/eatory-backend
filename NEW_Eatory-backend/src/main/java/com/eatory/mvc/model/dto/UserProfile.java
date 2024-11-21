package com.eatory.mvc.model.dto;

import java.util.ArrayList;
import java.util.List;

public class UserProfile {
	private String username;
	private String profileImage;
	private int postCount;
	private int followerCount;
	private int followeeCount;
	private List<String> allergies = new ArrayList<>();
	private int height;
	private int weight;
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
	public void setFolloweeCount(int followeeCount) {
		this.followeeCount = followeeCount;
	}
	public List<String> getAllergies() {
		return allergies;
	}
	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
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
	
	

}
