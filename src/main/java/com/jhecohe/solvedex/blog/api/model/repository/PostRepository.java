package com.jhecohe.solvedex.blog.api.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jhecohe.solvedex.blog.api.model.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

}
