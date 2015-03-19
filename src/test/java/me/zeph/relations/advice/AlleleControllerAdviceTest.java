package me.zeph.relations.advice;

import me.zeph.relations.exception.AlleleNotFoundException;
import me.zeph.relations.exception.KitNotFoundException;
import me.zeph.relations.model.api.ApiError;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AlleleControllerAdviceTest {

	private static final String ERROR_MESSAGE = "error";
	private AlleleControllerAdvice alleleControllerAdvice;

	@Before
	public void setUp() throws Exception {
		alleleControllerAdvice = new AlleleControllerAdvice();
	}

	@Test
	public void shouldHandleAlleleNotFoundException() {
		ApiError error = alleleControllerAdvice.handleAlleleNotFoundException(new AlleleNotFoundException(ERROR_MESSAGE));
		assertThat(error.getMessage(), is(ERROR_MESSAGE));
	}

	@Test
	public void shouldHandleKitNotFoundException() {
		ApiError error = alleleControllerAdvice.handleKitNotFoundException(new KitNotFoundException(ERROR_MESSAGE));
		assertThat(error.getMessage(), is(ERROR_MESSAGE));
	}

}