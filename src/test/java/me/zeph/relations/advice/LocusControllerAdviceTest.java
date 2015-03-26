package me.zeph.relations.advice;

import me.zeph.relations.exception.KitNotFoundException;
import me.zeph.relations.exception.LocusAlreadyExistException;
import me.zeph.relations.exception.LocusNotFoundException;
import me.zeph.relations.model.api.ApiError;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class LocusControllerAdviceTest {

	private static final String ERROR_MESSAGE = "error";
	private LocusControllerAdvice locusControllerAdvice;

	@Before
	public void setUp() throws Exception {
		locusControllerAdvice = new LocusControllerAdvice();
	}

	@Test
	public void shouldHandleLocusNotFoundException() {
		ApiError error = locusControllerAdvice.handleLocusNotFoundException(new LocusNotFoundException(ERROR_MESSAGE));
		assertThat(error.getMessage(), is(ERROR_MESSAGE));
	}

	@Test
	public void shouldHandleKitNotFoundException() {
		ApiError error = locusControllerAdvice.handleKitNotFoundException(new KitNotFoundException(ERROR_MESSAGE));
		assertThat(error.getMessage(), is(ERROR_MESSAGE));
	}

	@Test
	public void shouldHandelLocusAlreadyExistException() {
		ApiError error = locusControllerAdvice.handleLocusAlreadyExistException(
				new LocusAlreadyExistException(ERROR_MESSAGE));
		assertThat(error.getMessage(), is(ERROR_MESSAGE));
	}

}