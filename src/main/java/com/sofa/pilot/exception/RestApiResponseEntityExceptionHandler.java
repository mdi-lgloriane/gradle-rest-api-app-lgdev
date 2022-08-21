package com.sofa.pilot.exception;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sofa.pilot.domain.ErrorResponse;

@ControllerAdvice
public class RestApiResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { EntityNotFoundException.class })
	protected ResponseEntity<Object> handleEntityNotFOund(RuntimeException ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.add(ex.getMessage());
		return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse();
		List<ObjectError> objectErrors = ex.getAllErrors();
		objectErrors.forEach(error -> {
			String errorMessage;
			if (error instanceof FieldError fieldError) {
				errorMessage = fieldError.getField() + " " + fieldError.getDefaultMessage();
			} else {
				errorMessage = error.getDefaultMessage();
			}
			errorResponse.add(errorMessage);
		});
		return handleExceptionInternal(ex, errorResponse, headers, status, request);
	}
}
