package com.jhecohe.solvedex.blog.api.model.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jhecohe.solvedex.blog.api.model.entity.Comment;
import com.jhecohe.solvedex.blog.api.model.entity.Post;

public interface CommentRepository extends JpaRepository<Comment, Long>{

	Page<Comment> findAllByPost(Post post, Pageable pageable);
}
