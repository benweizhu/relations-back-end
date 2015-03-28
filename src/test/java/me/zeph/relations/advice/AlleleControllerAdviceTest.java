package me.zeph.relations.advice;

import me.zeph.relations.exception.AlleleAlreadyExistException;
import me.zeph.relations.exception.AlleleNotFoundException;
import me.zeph.relations.exception.LocusNotFoundException;
import me.zeph.relations.model.api.ApiError;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AlleleControllerAdviceTest {

	private static final String ERROR = "error";
	private AlleleControllerAdvice alleleControllerAdvice;

	@Before
	public void setUp() throws Exception {
		alleleControllerAdvice = new AlleleControllerAdvice();
	}

	@Test
	public void shouldHandleLocusNotFoundException() {
		ApiError apiError = alleleControllerAdvice.handleLocusNotFoundException(new LocusNotFoundException(ERROR));
		assertThat(apiError.getMessage(), is(ERROR));
	}

	@Test
	public void shouldHandleAlleleNotFoundException() {
		ApiError apiError = alleleControllerAdvice.handleAlleleNotFoundException(new AlleleNotFoundException(ERROR));
		assertThat(apiError.getMessage(), is(ERROR));
	}

	@Test
	public void shouldHandleAlleleAlreadyExistException() {
		ApiError apiError = alleleControllerAdvice.handleAlleleAlreadyExistException(new AlleleAlreadyExistException(ERROR));
		assertThat(apiError.getMessage(), is(ERROR));
	}
}