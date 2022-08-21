package com.sofa.pilot.service;

import java.util.List;

public interface CrudService<I, O> {

	O create(I input);

	O read(String id);

	List<O> read();

	O update(I input, String id);

	void delete(String id);
}
