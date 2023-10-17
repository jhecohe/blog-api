package com.jhecohe.solvedex.blog.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhecohe.solvedex.blog.api.dto.RegisteredUserDto;
import com.jhecohe.solvedex.blog.api.dto.UserDto;
import com.jhecohe.solvedex.blog.api.service.auth.AuthenticationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final AuthenticationService authenticationService;
	
	@PostMapping("register")
	public ResponseEntity<RegisteredUserDto> registerUser(@RequestBody @Valid UserDto userDto){
		
		RegisteredUserDto registeredUser = authenticationService.registerUser(userDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
	}
}
