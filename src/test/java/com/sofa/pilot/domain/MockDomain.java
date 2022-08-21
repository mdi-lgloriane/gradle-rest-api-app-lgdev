package com.sofa.pilot.domain;

import org.springframework.beans.BeanUtils;

import com.sofa.pilot.dto.MockApiInput;
import com.sofa.pilot.dto.MockApiOutput;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MockDomain extends BaseDomain<MockApiOutput> {

	public static final String UPDATED_NAME = "UpdatedTestName";
	public static final String NAME = "TestName";
	public static final String ID = "TestId";

	private String name;

	public MockDomain() {
	}

	public MockDomain(MockApiInput mockApiInput) {
		BeanUtils.copyProperties(mockApiInput, this);
	}

	public MockApiOutput generateOutput() {
		MockApiOutput mockApiOutput = new MockApiOutput();
		BeanUtils.copyProperties(this, mockApiOutput);
		return mockApiOutput;
	}

	public static MockApiInput createApiInput() {
		MockApiInput mockApiInput = new MockApiInput();
		mockApiInput.setName(NAME);
		return mockApiInput;
	}

	public static MockDomain createDomain() {
		MockDomain mockDomain = new MockDomain();
		mockDomain.setName(NAME);
		mockDomain.setId(ID);
		return mockDomain;
	}
}
