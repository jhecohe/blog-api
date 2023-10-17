package com.jhecohe.solvedex.blog.api.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jhecohe.solvedex.blog.api.dto.PostDto;

public interface IPostService {

	Page<PostDto> getPosts(Pageable pageable);

	Optional<PostDto> getPostById(Long id);

	PostDto savePost(PostDto postDto);

	PostDto updatePost(PostDto postDto, Long id);

	void deletePost(Long id);

}