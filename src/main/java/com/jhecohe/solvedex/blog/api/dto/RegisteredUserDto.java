package com.jhecohe.solvedex.blog.api.dto;

import java.io.Serializable;

import lombok.Builder;

@Builder
public record RegisteredUserDto(Long id, String username, String name, String role, String jwt)
		implements Serializable {

}
