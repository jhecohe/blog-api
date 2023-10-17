package com.jhecohe.solvedex.blog.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jhecohe.solvedex.blog.api.dto.CommentDto;
import com.jhecohe.solvedex.blog.api.exception.ObjectNotFoundException;
import com.jhecohe.solvedex.blog.api.model.entity.Comment;
import com.jhecohe.solvedex.blog.api.model.entity.Post;
import com.jhecohe.solvedex.blog.api.model.repository.CommentRepository;
import com.jhecohe.solvedex.blog.api.model.repository.PostRepository;
import com.jhecohe.solvedex.blog.api.service.ICommentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService implements ICommentService {

	private final CommentRepository commentRepository;
	private final PostRepository postRepository;

	@Override
	public Page<CommentDto> getCommentsByPost(Long idPost, Pageable pageable) {
		Post post = postRepository.findById(idPost)
				.orElseThrow(() -> new ObjectNotFoundException("Comments not found fpr the POST with ID" + idPost));
		Page<Comment> commentList = commentRepository.findAllByPost(post, pageable);
		List<CommentDto> dtoList = commentList.getContent().stream().map(this::entityToDto).toList();

		return new PageImpl<>(dtoList, pageable, commentList.getTotalElements());
	}

	@Override
	public Optional<CommentDto> getCommentById(Long id) {
		return Optional.of(this.entityToDto(commentRepository.findById(id).get()));
	}

	@Override
	public CommentDto saveComment(CommentDto commentDto) {
		Comment commentSaved = commentRepository.save(dtoToEntity(commentDto));
		return entityToDto(commentSaved);
	}

	@Override
	public CommentDto updateComment(CommentDto commentDto, Long id) {
		Comment comment = commentRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Comment not found with the ID" + id));

		comment.setText(commentDto.text());
		Comment updatedComment = commentRepository.save(comment);
		return entityToDto(updatedComment);
	}

	@Override
	public void deleteComment(Long id) {
		commentRepository.deleteById(id);
	}

	private CommentDto entityToDto(Comment c) {
		if (c != null)
			return new CommentDto(c.getId(), c.getText(), c.getPost().getId());
		return null;
	}

	private Comment dtoToEntity(CommentDto dto) {
		if (dto != null) {
			Optional<Post> post = postRepository.findById(dto.postId());
			return Comment.builder().id(dto.id()).text(dto.text()).post(post.get()).build();
		}
		return null;
	}
}
