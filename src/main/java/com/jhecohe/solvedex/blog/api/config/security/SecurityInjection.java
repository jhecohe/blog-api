package com.jhecohe.solvedex.blog.api.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jhecohe.solvedex.blog.api.exception.ObjectNotFoundException;
import com.jhecohe.solvedex.blog.api.model.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityInjection {

	private final UserRepository userRepository;
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationStrategy = new DaoAuthenticationProvider();
		
		authenticationStrategy.setPasswordEncoder(passwordEncoder());
		authenticationStrategy.setUserDetailsService(userDetailsService());
		
		return authenticationStrategy;
	}

    @Bean
    PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
    
    @Bean
    UserDetailsService userDetailsService() {
    	return (username) -> {
    		return userRepository.findByUsername(username)
    				.orElseThrow(() -> new ObjectNotFoundException(" User not found with username " + username));
    	};
    }
}
