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

import com.jhecohe.solvedex.blog.api.dto.CommentDto;
import com.jhecohe.solvedex.blog.api.service.ICommentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
	
	private final ICommentService commentService;

	@GetMapping("/post/{idPost}")
	ResponseEntity<Page<CommentDto>> getCommentsByPost(@PathVariable Long idPost, Pageable pageable){
		
		Page<CommentDto> commentPage = commentService.getCommentsByPost(idPost, pageable);
		if(commentPage.hasContent()) return ResponseEntity.ok(commentPage);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	ResponseEntity<CommentDto> getCommentById(@PathVariable Long id){
		
		Optional<CommentDto> post = commentService.getCommentById(id);
		if(post.isPresent()) return ResponseEntity.ok(post.get());
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	ResponseEntity<CommentDto> saveComment(@RequestBody @Valid CommentDto commentDto){
		
		CommentDto comment = commentService.saveComment(commentDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(comment);
	}
	
	@PutMapping("/{id}")
	ResponseEntity<CommentDto> updateComment(@RequestBody @Valid CommentDto commentDto, @PathVariable Long id){
		
		CommentDto comment = commentService.updateComment(commentDto, id);
		return ResponseEntity.ok(comment);
	}
	
	@DeleteMapping("/{id}")
	ResponseEntity<CommentDto> deleteComment(@PathVariable Long id){
		
		commentService.deleteComment(id);
		return ResponseEntity.ok().build();
	}
}
