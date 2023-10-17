package com.jhecohe.solvedex.blog.api.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhecohe.solvedex.blog.api.dto.PostDto;
import com.jhecohe.solvedex.blog.api.model.entity.Post;
import com.jhecohe.solvedex.blog.api.service.IPostService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
	
	private final IPostService postService;

	@GetMapping
	ResponseEntity<Page<PostDto>> getPosts(Pageable pageable){
		
		Page<PostDto> postPage = postService.getPosts(pageable);
		if(postPage.hasContent()) return ResponseEntity.ok(postPage);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	ResponseEntity<PostDto> getPostsById(@PathVariable Long id){
		
		Optional<PostDto> post = postService.getPostById(id);
		if(post.isPresent()) return ResponseEntity.ok(post.get());
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	ResponseEntity<PostDto> savePost(@RequestBody @Valid PostDto postDto){
		
		PostDto post = postService.savePost(postDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(post);
	}
	
	@PutMapping("/{id}")
	ResponseEntity<PostDto> updatePost(@RequestBody @Valid PostDto postDto, @PathVariable Long id){
		
		PostDto post = postService.updatePost(postDto, id);
		return ResponseEntity.ok(post);
	}
	
	@DeleteMapping("/{id}")
	ResponseEntity<Post> deletePost(@PathVariable Long id){
		
		postService.deletePost(id);
		return ResponseEntity.ok().build();
	}
}
