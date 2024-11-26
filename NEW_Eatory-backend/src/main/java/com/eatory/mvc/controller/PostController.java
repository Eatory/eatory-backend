package com.eatory.mvc.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eatory.mvc.model.dto.PostDto;
import com.eatory.mvc.model.service.PostService;

@RestController
@RequestMapping("/posts")
@CrossOrigin("*")
public class PostController {
	private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 게시글 생성
    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody PostDto postDTO) {
        postService.createPost(postDTO);
        return ResponseEntity.ok("게시글이 성공적으로 생성되었습니다.");
    }

    // 게시글 단건 조회
    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    // 전체 게시글 조회
    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    // 게시글 수정
    @PutMapping("/{postId}")
    public ResponseEntity<String> updatePost(@PathVariable Long postId, @RequestBody PostDto postDTO) {
        postDTO.setPostId(postId);
        postService.updatePost(postDTO);
        return ResponseEntity.ok("게시글이 성공적으로 수정되었습니다.");
    }

    // 게시글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok("게시글이 성공적으로 삭제되었습니다.");
    }
}
