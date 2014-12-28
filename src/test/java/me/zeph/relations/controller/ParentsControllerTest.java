package me.zeph.relations.controller;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ParentsControllerTest {
	@Test
	public void shouldGoToParentsPage() {
		ParentsController parentsController = new ParentsController();
		String viewName = parentsController.view();
		assertThat(viewName, is("parents"));
	}
}