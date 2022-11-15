package com.springboot.example.mysqlconnection;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3230771463636737235L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
