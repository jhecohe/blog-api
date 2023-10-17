package com.jhecohe.solvedex.blog.api.service.impl;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jhecohe.solvedex.blog.api.dto.UserDto;
import com.jhecohe.solvedex.blog.api.exception.InvalidPasswordException;
import com.jhecohe.solvedex.blog.api.model.entity.User;
import com.jhecohe.solvedex.blog.api.model.repository.UserRepository;
import com.jhecohe.solvedex.blog.api.model.util.Role;
import com.jhecohe.solvedex.blog.api.service.IUserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public User registerUser(@Valid UserDto userDto) {
		
		validatePassword(userDto);
		
		User user = User.builder()
		.username(userDto.username())
		.name(userDto.name())
		.role(Role.valueOf(userDto.role()))
		.password(passwordEncoder.encode(userDto.password()))
		.build();
		
		return userRepository.save(user);
	}
	
	@Override
	public Optional<User> findOneByUsername(String username) {
        return userRepository.findByUsername(username);
    }

	private void validatePassword(UserDto userDto) {
		
		if(!StringUtils.hasText(userDto.password()) || !StringUtils.hasText(userDto.passwordValidation())) {
			throw new InvalidPasswordException("Password doesn't match");
		}
		if(!userDto.password().equals(userDto.passwordValidation())) {
			throw new InvalidPasswordException("Password doesn't match");
		}
	}

}
