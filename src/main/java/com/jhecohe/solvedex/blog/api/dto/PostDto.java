package com.jhecohe.solvedex.blog.api.dto;

import java.io.Serializable;
import java.util.List;

import com.jhecohe.solvedex.blog.api.model.entity.Comment;

import jakarta.validation.constraints.NotBlank;

public record PostDto(Long id, @NotBlank String title, @NotBlank String content, List<Comment> comments)
		implements Serializable {

}
