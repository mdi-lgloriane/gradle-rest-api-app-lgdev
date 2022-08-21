package com.sofa.pilot.service;

import static com.sofa.pilot.domain.MockDomain.ID;
import static com.sofa.pilot.domain.MockDomain.NAME;
import static com.sofa.pilot.domain.MockDomain.createApiInput;
import static com.sofa.pilot.domain.MockDomain.createDomain;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.sofa.pilot.domain.MockDomain;
import com.sofa.pilot.dto.MockApiInput;
import com.sofa.pilot.dto.MockApiOutput;
import com.sofa.pilot.exception.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
class BaseCrudServiceTest {

	private static final String ERROR_CREATING_DOMAIN = "Error encountered while creating domain instance";
	private static final String MOCK_DOMAIN_NOT_FOUND = "MockDomain not found";

	@Mock
	private MongoRepository<MockDomain, String> mongoRepository;

	@Mock
	private MongoRepository<DomainWithIssue, String> mongoRepositoryWithIssue;

	private CrudService<MockApiInput, MockApiOutput> crudService;
	private MockDomain mockDomain;

	@BeforeEach
	public void init() {
		crudService = new MockBaseCrudService(mongoRepository);
		mockDomain = createDomain();
	}

	@Test
	void givenMongoRepositoryWhenCreateIsCalledThenIdIsReturned() {
		MockApiInput apiInput = createApiInput();
		when(mongoRepository.insert(any(MockDomain.class))).thenReturn(mockDomain);

		MockApiOutput result = crudService.create(apiInput);

		assertSame(mockDomain.getId(), result.getId());
		assertSame(mockDomain.getName(), result.getName());
	}

	@Test
	void givenMongoRepositoryWhenReadWithIdIsCalledThenStudentOutputIsReturned() {
		when(mongoRepository.findById(mockDomain.getId())).thenReturn(Optional.of(mockDomain));

		MockApiOutput result = crudService.read(mockDomain.getId());

		assertSame(mockDomain.getId(), result.getId());
		assertSame(mockDomain.getName(), result.getName());
	}

	@Test
	void givenMongoRepositoryWhenReadWithIdIsCalledThenNullIsReturned() {
		String id = mockDomain.getId();
		when(mongoRepository.findById(id)).thenReturn(Optional.empty());

		Exception exception = assertThrows(EntityNotFoundException.class, () -> {
			crudService.read(id);
		});

		assertEquals(MOCK_DOMAIN_NOT_FOUND, exception.getMessage());
	}

	@Test
	void givenMongoRepositoryWhenReadIsCalledThenStudentOutputsIsReturned() {
		List<MockDomain> domains = Collections.singletonList(mockDomain);
		when(mongoRepository.findAll()).thenReturn(domains);

		List<MockApiOutput> results = crudService.read();

		assertSame(domains.size(), results.size());
		MockApiOutput result = results.get(0);
		MockDomain domain = domains.get(0);
		assertSame(domain.getId(), result.getId());
		assertSame(domain.getName(), result.getName());
	}

	@Test
	void givenMongoRepositoryWhenDomainExistsThenSaveIsInvoked() {
		MockApiInput apiInput = createApiInput();
		when(mongoRepository.existsById(ID)).thenReturn(true);
		when(mongoRepository.save(any(MockDomain.class))).thenReturn(mockDomain);

		MockApiOutput result = crudService.update(apiInput, ID);

		assertSame(ID, result.getId());
		assertSame(NAME, result.getName());
	}

	@Test
	void givenMongoRepositoryWhenDomainDoesNotExistsThenEntityNotFoundExceptionIsThrown() {
		MockApiInput apiInput = createApiInput();
		when(mongoRepository.existsById(ID)).thenReturn(false);

		Exception exception = assertThrows(EntityNotFoundException.class, () -> {
			crudService.update(apiInput, ID);
		});

		assertEquals(MOCK_DOMAIN_NOT_FOUND, exception.getMessage());
	}

	@Test
	void givenMongoRepositoryWhenDeleteIsExecutedThenRepositoryDeleteIsInvoked() {
		crudService.delete(ID);

		verify(mongoRepository, times(1)).deleteById(ID);
	}

	@Test
	void givenApiInputWhenCreateDomainIsInvokedThenDomainIsReturned() {
		MockApiInput mockApiInput = createApiInput();
		BaseCrudService<MockDomain, MockApiInput, MockApiOutput> baseCrudService = new BaseCrudService<>(
				mongoRepository, MockDomain.class) {
		};

		MockDomain result = baseCrudService.createDomain(mockApiInput);

		assertSame(mockApiInput.getName(), result.getName());
	}

	@Test
	void givenApiInputWhenCreateDomainIsInvokedThenExceptionIsThrown() {
		MockApiInput mockApiInput = createApiInput();
		BaseCrudService<DomainWithIssue, MockApiInput, MockApiOutput> baseCrudService = new BaseCrudService<>(
				mongoRepositoryWithIssue, DomainWithIssue.class) {
		};

		Exception exception = assertThrows(RuntimeException.class, () -> {
			baseCrudService.createDomain(mockApiInput);
		});

		assertSame(ERROR_CREATING_DOMAIN, exception.getMessage());
	}

	private class MockBaseCrudService extends BaseCrudService<MockDomain, MockApiInput, MockApiOutput> {

		protected MockBaseCrudService(MongoRepository<MockDomain, String> mongoRepository) {
			super(mongoRepository, MockDomain.class);
		}

		MockDomain createDomain(MockApiInput mockApiInput) {
			return mockDomain;
		}
	}

	private static class DomainWithIssue extends MockDomain {
	}
}
