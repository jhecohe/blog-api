package com.jhecohe.solvedex.blog.api.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;

public record CommentDto(Long id, @NotBlank String text, Long postId) implements Serializable{

}
