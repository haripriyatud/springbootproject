package com.springboot.example.mysqlconnection.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {

		ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

		return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);

	}

	
	@ExceptionHandler(InvalidRequestException.class)
	public final ResponseEntity<ExceptionResponse> InvalidRequestException(InvalidRequestException ex, WebRequest request) {

		ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);

	}
}
