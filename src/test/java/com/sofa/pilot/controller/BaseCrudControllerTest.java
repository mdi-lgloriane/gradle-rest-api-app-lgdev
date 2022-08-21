package com.sofa.pilot.controller;

import static com.sofa.pilot.domain.MockDomain.ID;
import static com.sofa.pilot.domain.MockDomain.NAME;
import static com.sofa.pilot.domain.MockDomain.createApiInput;
import static com.sofa.pilot.domain.MockDomain.createDomain;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.sofa.pilot.domain.MockDomain;
import com.sofa.pilot.dto.MockApiInput;
import com.sofa.pilot.dto.MockApiOutput;
import com.sofa.pilot.service.CrudService;

@ExtendWith(MockitoExtension.class)
class BaseCrudControllerTest {

	@Mock
	private CrudService<MockApiInput, MockApiOutput> crudService;

	private CrudController<MockApiInput, MockApiOutput> crudController;
	private MockApiInput mockApiInput;
	private MockDomain mockDomain;
	private MockApiOutput mockOutput;

	@BeforeEach
	void init() {
		crudController = new MockCrudController(crudService);
		mockApiInput = createApiInput();
		mockDomain = createDomain();
		mockOutput = mockDomain.generateOutput();
	}

	@Test
	void givenMockApiInputWhenCreateIsInvokedThenMockApiOutputIsReturned() {
		when(crudService.create(mockApiInput)).thenReturn(mockOutput);

		ResponseEntity<MockApiOutput> responseEntity = crudController.create(mockApiInput);
		MockApiOutput result = responseEntity.getBody();

		assertSame(mockDomain.getId(), result.getId());
		assertSame(mockDomain.getName(), result.getName());
	}

	@Test
	void givenIdWhenReadIsInvokedThenMockApiOutputIsReturned() {
		when(crudService.read(ID)).thenReturn(mockOutput);

		ResponseEntity<MockApiOutput> responseEntity = crudController.read(ID);
		MockApiOutput result = responseEntity.getBody();

		assertSame(ID, result.getId());
		assertSame(NAME, result.getName());
	}

	@Test
	void givenCrudServiceWhenReadIsInvokedThenMockApiOutputIsReturned() {
		List<MockApiOutput> mockApiOutputs = Collections.singletonList(mockOutput);
		when(crudService.read()).thenReturn(mockApiOutputs);

		ResponseEntity<List<MockApiOutput>> responseEntity = crudController.read();
		List<MockApiOutput> results = responseEntity.getBody();
		MockApiOutput result = results.get(0);

		assertSame(ID, result.getId());
		assertSame(NAME, result.getName());
	}

	@Test
	void givenMockApiInputWhenUpdateIsInvokedThenMockApiOutputIsReturned() {
		when(crudService.update(mockApiInput, ID)).thenReturn(mockOutput);

		ResponseEntity<MockApiOutput> responseEntity = crudController.update(mockApiInput, ID);
		MockApiOutput result = responseEntity.getBody();

		assertSame(mockDomain.getId(), result.getId());
		assertSame(mockDomain.getName(), result.getName());
	}

	@Test
	void givenIdWhenDeleteIsInvokedThenCrudServiceDeleteIsCalled() {
		crudController.delete(ID);

		verify(crudService, times(1)).delete(ID);
	}

	private class MockCrudController extends BaseCrudController<MockApiInput, MockApiOutput> {

		protected MockCrudController(CrudService<MockApiInput, MockApiOutput> crudService) {
			super(crudService);
		}
	}
}
