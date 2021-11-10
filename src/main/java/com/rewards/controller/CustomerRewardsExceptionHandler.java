package com.rewards.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rewards.exception.CustomerRewardsException;

@ControllerAdvice
public class CustomerRewardsExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(value= {CustomerRewardsException.class})
	protected ResponseEntity<Object> handleException (RuntimeException rex, WebRequest request) {
		String responseBody = rex.getMessage();
		return handleExceptionInternal(rex, responseBody, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
}