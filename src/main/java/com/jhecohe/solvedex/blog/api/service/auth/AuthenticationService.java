package com.jhecohe.solvedex.blog.api.service.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.jhecohe.solvedex.blog.api.dto.RegisteredUserDto;
import com.jhecohe.solvedex.blog.api.dto.UserDto;
import com.jhecohe.solvedex.blog.api.dto.auth.AuthenticationRequest;
import com.jhecohe.solvedex.blog.api.dto.auth.AuthenticationResponse;
import com.jhecohe.solvedex.blog.api.exception.ObjectNotFoundException;
import com.jhecohe.solvedex.blog.api.model.entity.User;
import com.jhecohe.solvedex.blog.api.service.IUserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final IUserService userService;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	
	public RegisteredUserDto registerUser(@Valid UserDto userDto) {
		
		User user = userService.registerUser(userDto);
		
		String jwt = jwtService.generateToken(user, genereteExtraClaims(user));
		
		return RegisteredUserDto.builder()
		.id(user.getId())
		.name(user.getName())
		.username(user.getUsername())
		.role(user.getRole().name())
		.jwt(jwt)
		.build();
	}

	private Map<String, Object> genereteExtraClaims(User user) {
		
		Map<String, Object> extraClaims = new HashMap<>();
		
		extraClaims.put("name", user.getUsername());
		extraClaims.put("role", user.getRole().name());
		extraClaims.put("authorities", user.getAuthorities());
		
		return extraClaims;
	}

	public boolean validateToken(String jwt) {
        try{
            jwtService.extractUsername(jwt);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
	
	public AuthenticationResponse login(AuthenticationRequest autRequest) {

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                autRequest.username(), autRequest.password()
        );

        authenticationManager.authenticate(authentication);

        UserDetails user = userService.findOneByUsername(autRequest.username()).get();
        String jwt = jwtService.generateToken(user, generateExtraClaims((User) user));

        AuthenticationResponse authRsp = new AuthenticationResponse(jwt);

        return authRsp;
    }
	
	public User findLoggedInUser() {
        UsernamePasswordAuthenticationToken auth =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        String username = (String) auth.getPrincipal();
        return userService.findOneByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("User not found. Username: " + username));
    }
	
	private Map<String, Object> generateExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name",user.getName());
        extraClaims.put("role",user.getRole().name());
        extraClaims.put("authorities",user.getAuthorities());

        return extraClaims;
    }
}
