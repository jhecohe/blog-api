package com.jhecohe.solvedex.blog.api.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jhecohe.solvedex.blog.api.dto.CommentDto;

public interface ICommentService {

	Page<CommentDto> getCommentsByPost(Long idPost, Pageable pageable);

	Optional<CommentDto> getCommentById(Long id);

	CommentDto saveComment(CommentDto postDto);

	CommentDto updateComment(CommentDto postDto, Long id);

	void deleteComment(Long id);

}