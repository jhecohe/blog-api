package com.jhecohe.solvedex.blog.api.dto;

import java.io.Serializable;

import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UserDto(@Size(min = 4) String name, @Size(min = 4) String username, @Size(min = 6) String password,
		@Size(min = 6) String passwordValidation, String role) implements Serializable {

}
