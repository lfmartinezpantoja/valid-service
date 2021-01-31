package com.valid.clients.exception;

import static com.valid.clients.error.ErrorDescription.GENERAL_EXCEPTION;
import static com.valid.clients.error.ErrorDescription.VALIDATION_EXCEPTION;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.valid.clients.dto.ErrorDTO;

import lombok.extern.java.Log;

@Log
@RestControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

	@ExceptionHandler(value = CustomExpection.class)
	public ResponseEntity<ErrorDTO> customException(CustomExpection customException) {
		log.info(String.format("Ocurred an error controlled, response information: message: %s, HttpStatus: %s",
				customException.getMessage(), customException.getHttpStatus()));
		return new ResponseEntity<>(new ErrorDTO(customException.getMessage(), false), customException.getHttpStatus());

	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDTO> generalException(MethodArgumentNotValidException exception) {
		
		String errors = exception.getBindingResult().getAllErrors().stream().map(m -> m.getDefaultMessage())
				.collect(Collectors.joining(", "));
		log.info("Ocurred an error valitation input Json, ERRORS:  errors");
		return new ResponseEntity<>(new ErrorDTO(String.format(VALIDATION_EXCEPTION.getMessage(), errors), false),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorDTO> generalException(Exception exception) {
		log.info(String.format("Ocurred an uncontrolled error , information about error, cause: %s, tracing: %s",
				exception.getCause(), exception.getStackTrace()));
		return new ResponseEntity<>(new ErrorDTO(GENERAL_EXCEPTION.getMessage(), false),
				HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
