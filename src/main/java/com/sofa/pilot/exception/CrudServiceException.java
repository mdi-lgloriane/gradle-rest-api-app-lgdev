package com.sofa.pilot.exception;

public class CrudServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CrudServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
