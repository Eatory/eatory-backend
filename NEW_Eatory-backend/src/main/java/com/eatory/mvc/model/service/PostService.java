package com.eatory.mvc.model.service;

import java.util.List;

import com.eatory.mvc.model.dto.PostDto;

public interface PostService {
	
	void createPost(PostDto postDto); //게시글 생성
	
	PostDto getPostById(Long postId); // 게시글 단건 조회

    List<PostDto> getAllPosts(); // 전체 게시글 조회

    void updatePost(PostDto postDTO); // 게시글 수정

    void deletePost(Long postId); // 게시글 삭제
	

}
