package com.mastercard.user.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mastercard.user.model.ErrorResponse;

@ControllerAdvice
public class UserResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserException.class)
	public ResponseEntity<Object> handleUserException(final UserException ex, final WebRequest request) {
		return new ResponseEntity<>(new ErrorResponse("USER_NOT_FOUND", "User Not found", List.of(ex.getMessage())),
				HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {

		// Get all fields level errors
		final List<String> errors = ex.getBindingResult().getFieldErrors().stream()
				.map(message -> message.getDefaultMessage()).collect(Collectors.toList());

		return new ResponseEntity<>(new ErrorResponse("VALIDATION_ERROR", "Validation Error", errors),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleUnHandledException(final Exception ex, final WebRequest request) {
		return new ResponseEntity<>(new ErrorResponse("TRANSIENT_ERROR", ex.getMessage(), new ArrayList<>()),
				HttpStatus.BAD_REQUEST);
	}

}
