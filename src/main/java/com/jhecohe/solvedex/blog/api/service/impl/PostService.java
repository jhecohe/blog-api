package com.jhecohe.solvedex.blog.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jhecohe.solvedex.blog.api.dto.PostDto;
import com.jhecohe.solvedex.blog.api.exception.ObjectNotFoundException;
import com.jhecohe.solvedex.blog.api.model.entity.Post;
import com.jhecohe.solvedex.blog.api.model.repository.PostRepository;
import com.jhecohe.solvedex.blog.api.service.IPostService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService implements IPostService {

	private final PostRepository postRepository;

	@Override
	public Page<PostDto> getPosts(Pageable pageable) {
		Page<Post> postList = postRepository.findAll(pageable);
		List<PostDto> dtoList = postList.getContent().stream().map(this::entityToDto).toList();

		return new PageImpl<>(dtoList, pageable, postList.getTotalElements());
	}

	@Override
	public Optional<PostDto> getPostById(Long id) {
		return Optional.of(this.entityToDto(postRepository.findById(id).get()));
	}

	@Override
	public PostDto savePost(PostDto postDto) {
		Post postSaved = postRepository.save(dtoToEntity(postDto));
		return entityToDto(postSaved);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Long id) {
		Post post = postRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Post not found with the ID" + id));

		post.setTitle(postDto.title());
		post.setContent(postDto.content());
		Post postUpdated = postRepository.save(post);
		return entityToDto(postUpdated);
	}

	@Override
	public void deletePost(Long id) {
		postRepository.deleteById(id);
	}

	private PostDto entityToDto(Post p) {
		if (p != null)
			return new PostDto(p.getId(), p.getTitle(), p.getContent(), p.getComments());
		return null;
	}

	private Post dtoToEntity(PostDto dto) {
		if (dto != null)
			return Post.builder().title(dto.title()).content(dto.content()).build();
		return null;
	}
}
