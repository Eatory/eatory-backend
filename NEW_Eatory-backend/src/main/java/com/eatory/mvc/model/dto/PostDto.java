package com.eatory.mvc.model.dto;

public class PostDto {
	private Long postId;
	private Long userId;
	private String postTime;
	private String image;
	private String content;
	public Long getPostId() {
		return postId;
	}
	public void setPostId(Long postId) {
		this.postId = postId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getPostTime() {
		return postTime;
	}
	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "PostDto [postId=" + postId + ", userId=" + userId + ", postTime=" + postTime + ", image=" + image
				+ ", content=" + content + "]";
	}
	
	
	

}
