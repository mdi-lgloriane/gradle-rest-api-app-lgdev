package com.sofa.pilot.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class PropertyLoaderTest {

	private static final String TEST_KEY = "MONGO_PORT";

	@Test
	void givenPropertyLoaderWhenGetValueOfIsInvokedThenExpectedResultIsReturned() {
		PropertyLoader propertyLoader = new PropertyLoader();

		String result = propertyLoader.getValueOf(TEST_KEY);

		assertNotNull(result);
	}
}
