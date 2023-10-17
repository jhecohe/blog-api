package com.jhecohe.solvedex.blog.api.service;

import java.util.Optional;

import com.jhecohe.solvedex.blog.api.dto.UserDto;
import com.jhecohe.solvedex.blog.api.model.entity.User;

public interface IUserService {

	User registerUser(UserDto userDto);

	Optional<User> findOneByUsername(String username);

}