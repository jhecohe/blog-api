package com.jhecohe.solvedex.blog.api.exception;

import java.time.LocalTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jhecohe.solvedex.blog.api.dto.ApiException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handlerExceptionGeneric(Exception exception, HttpServletRequest request){
		
		ApiException apiException = ApiException.builder()
				.messageServer(exception.getLocalizedMessage())
				.url(request.getRequestURL().toString())
				.method(request.getMethod())
				.message("Internal Server Error, please try later ")
				.timestamp(LocalTime.now())
				.build();
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiException);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handlerExceptionArguments(MethodArgumentNotValidException exception, HttpServletRequest request){
		
		ApiException apiException = ApiException.builder()
				.messageServer(exception.getLocalizedMessage())
				.url(request.getRequestURL().toString())
				.method(request.getMethod())
				.message("Error in the request")
				.timestamp(LocalTime.now())
				.build();
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiException);
	}
}
