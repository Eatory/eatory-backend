package com.eatory.mvc.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eatory.mvc.model.dao.PostDao;
import com.eatory.mvc.model.dto.PostDto;

@Service
public class PostServiceImpl implements PostService{

	private final PostDao postDao;

    public PostServiceImpl(PostDao postDao) {
        this.postDao = postDao;
    }

    @Override
    @Transactional
    public void createPost(PostDto postDTO) {
        postDao.insertPost(postDTO);
    }

    @Override
    public PostDto getPostById(Long postId) {
        return postDao.selectPostById(postId);
    }

    @Override
    public List<PostDto> getAllPosts() {
        return postDao.selectAllPosts();
    }

    @Override
    @Transactional
    public void updatePost(PostDto postDTO) {
        postDao.updatePost(postDTO);
    }

    @Override
    @Transactional
    public void deletePost(Long postId) {
        postDao.deletePost(postId);
    }
	

}
