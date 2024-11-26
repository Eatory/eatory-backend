package com.eatory.mvc.model.dao;

import java.util.List;

import com.eatory.mvc.model.dto.PostDto;

public interface PostDao {
	void insertPost(PostDto postDto); //게시글 삽입
	
	PostDto selectPostById(Long postId); // 게시글 조회 (ID)

    List<PostDto> selectAllPosts(); // 전체 게시글 조회

    void updatePost(PostDto postDTO); // 게시글 수정

    void deletePost(Long postId); // 게시글 삭제
	

}
