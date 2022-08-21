package com.sofa.pilot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MockApiOutput extends BaseMockDto implements ApiOutput {
	private String id;
}
