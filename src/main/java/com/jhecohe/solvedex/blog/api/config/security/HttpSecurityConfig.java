package com.jhecohe.solvedex.blog.api.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

import com.jhecohe.solvedex.blog.api.config.security.filter.JwtAuthenticationFilter;
import com.jhecohe.solvedex.blog.api.model.util.RolePermissions;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class HttpSecurityConfig {

	private final AuthenticationProvider daoAuthProvider;
	private final JwtAuthenticationFilter jwtAuthenticationFilter;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		return http.csrf(csrfConfig -> csrfConfig.disable())
				.sessionManagement(sConfig -> sConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(daoAuthProvider)
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.authorizeHttpRequests(authReqConfig -> {
					buildRequestMatchers(authReqConfig);
				}).build();

	}

	private static void buildRequestMatchers(
			AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authReqConfig) {

		// Authorization endpoints to posts
		authReqConfig.requestMatchers(RegexRequestMatcher.regexMatcher(HttpMethod.GET, "/posts/[0-9]*"))
				.hasAuthority(RolePermissions.UPDATE_POST.name());

		authReqConfig.requestMatchers(HttpMethod.POST, "/posts").hasAuthority(RolePermissions.CREATE_POST.name());
		
		authReqConfig.requestMatchers(HttpMethod.PUT, "/posts/{postsId}")
				.hasAuthority(RolePermissions.UPDATE_POST.name());

		authReqConfig.requestMatchers(HttpMethod.DELETE, "/products/{productId}")
				.hasAuthority(RolePermissions.DELETE_POST.name());

		// Authorization endpoints to comments
		authReqConfig.requestMatchers(HttpMethod.POST, "/comment").hasAuthority(RolePermissions.CREATE_COMMENT.name());

		authReqConfig.requestMatchers(HttpMethod.PUT, "/comment/{categoryId}").hasAuthority(RolePermissions.UPDATE_COMMENT.name());

		authReqConfig.requestMatchers(HttpMethod.DELETE, "/comment/{categoryId}").hasAuthority(RolePermissions.DELETE_COMMMENT.name());

		/*
		 * Autorización de endpoints públicos
		 */
		authReqConfig.requestMatchers(HttpMethod.POST, "/users/register").permitAll();
		authReqConfig.requestMatchers(RegexRequestMatcher.regexMatcher(HttpMethod.GET, "/comment/post/[0-9]*")).permitAll();
		authReqConfig.requestMatchers(HttpMethod.GET, "/post").permitAll();
		authReqConfig.requestMatchers(HttpMethod.POST, "/auth/authenticate").permitAll();
		authReqConfig.requestMatchers(HttpMethod.GET, "/auth/validate-token").permitAll();
		authReqConfig.requestMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll();
		authReqConfig.requestMatchers(HttpMethod.GET, "/api-docs/**").permitAll();

		authReqConfig.anyRequest().authenticated();
	}
}
