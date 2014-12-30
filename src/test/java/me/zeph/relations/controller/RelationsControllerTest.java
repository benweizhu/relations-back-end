package me.zeph.relations.controller;

import me.zeph.relations.controller.web.RelationsController;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RelationsControllerTest {
	@Test
	public void shouldGoToRelationHomePage() {
		RelationsController relationsController = new RelationsController();
		String viewName = relationsController.view();
		assertThat(viewName, is("index"));
	}
}