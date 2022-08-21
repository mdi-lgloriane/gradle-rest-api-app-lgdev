package com.sofa.pilot.exception;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import com.sofa.pilot.domain.ErrorResponse;

@ExtendWith(MockitoExtension.class)
class RestApiResponseEntityExceptionHandlerTest {

	private static final String TEST_KEY = "testKey";
	private static final String TEST_VALUE = "testValue";
	private static final String MISSING_KEY = "Missing Key";
	private static final String MISSING_VALUE = "Missing Value";
	private static final String ENTITY_NOT_FOUND = "Entity not found";

	@Mock
	private RuntimeException runtimeException;
	@Mock
	private WebRequest request;
	@Mock
	private MethodArgumentNotValidException methodArgumentNotValidException;

	private RestApiResponseEntityExceptionHandler exceptionHandler;

	@BeforeEach
	public void init() {
		exceptionHandler = new RestApiResponseEntityExceptionHandler();
	}

	@Test
	void givenObjectErrorsWhenHandleMethodArgumentNotValidIsExecutedThenErrorsContainMissingKey() {
		List<ObjectError> objectErrors = Collections.singletonList(new ObjectError(TEST_KEY, MISSING_KEY));
		when(methodArgumentNotValidException.getAllErrors()).thenReturn(objectErrors);

		ResponseEntity<Object> result = exceptionHandler.handleMethodArgumentNotValid(methodArgumentNotValidException,
				new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

		ErrorResponse errorResponse = (ErrorResponse) result.getBody();
		List<String> errors = errorResponse.getErrors();
		assertSame(objectErrors.size(), errors.size());
		assertTrue(errors.contains(MISSING_KEY));
		assertSame(HttpStatus.BAD_REQUEST, result.getStatusCode());
	}

	@Test
	void givenObjectErrorsWhenHandleMethodArgumentNotValidIsExecutedThenErrorsContainMissingValue() {
		List<ObjectError> objectErrors = Collections.singletonList(new FieldError(TEST_KEY, TEST_VALUE, MISSING_VALUE));
		when(methodArgumentNotValidException.getAllErrors()).thenReturn(objectErrors);

		ResponseEntity<Object> result = exceptionHandler.handleMethodArgumentNotValid(methodArgumentNotValidException,
				new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

		ErrorResponse errorResponse = (ErrorResponse) result.getBody();
		List<String> errors = errorResponse.getErrors();
		assertSame(objectErrors.size(), errors.size());
		assertTrue(errors.contains(TEST_VALUE + " " + MISSING_VALUE));
		assertSame(HttpStatus.BAD_REQUEST, result.getStatusCode());
	}

	@Test
	void givenRuntimeExceptionWhenHandleEntityNotFOundIsExecutedThenNotFoundErrorIsReturned() {
		when(runtimeException.getMessage()).thenReturn(ENTITY_NOT_FOUND);

		ResponseEntity<Object> result = exceptionHandler.handleEntityNotFOund(runtimeException, request);

		ErrorResponse errorResponse = (ErrorResponse) result.getBody();
		List<String> errors = errorResponse.getErrors();
		assertTrue(errors.contains(ENTITY_NOT_FOUND));
		assertSame(HttpStatus.NOT_FOUND, result.getStatusCode());
	}
}
