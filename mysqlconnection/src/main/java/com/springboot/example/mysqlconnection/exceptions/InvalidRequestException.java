package com.springboot.example.mysqlconnection.exceptions;


import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)

public class InvalidRequestException extends RuntimeException {

	private static final long serialVersionUID = 8280554166988928245L;

	public InvalidRequestException(String message) {
		super(message);
	}
}
