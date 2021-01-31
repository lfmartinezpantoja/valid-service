package com.valid.clients.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomExpection extends RuntimeException {

	private final String message;
	private final HttpStatus httpStatus;
	private static final long serialVersionUID = 1L;
	

}
