package com.sofa.pilot.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ErrorResponse {
	private final List<String> errors = new ArrayList<>();

	public void add(String errorMessage) {
		errors.add(errorMessage);
	}

	public List<String> getErrors() {
		return Collections.unmodifiableList(errors);
	}
}
