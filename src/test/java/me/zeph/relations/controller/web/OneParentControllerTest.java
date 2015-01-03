package me.zeph.relations.controller.web;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class OneParentControllerTest {
	@Test
	public void shouldGoToOneParentPage() {
		//given
		OneParentController oneParentController = new OneParentController();
		//when
		String viewName = oneParentController.view();
		//then
		assertThat(viewName, is("oneParent"));
	}
}