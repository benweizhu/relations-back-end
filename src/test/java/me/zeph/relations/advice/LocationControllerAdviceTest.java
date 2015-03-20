package me.zeph.relations.advice;

import me.zeph.relations.exception.LocationNotFoundException;
import me.zeph.relations.model.api.ApiError;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class LocationControllerAdviceTest {

	private static final String ERROR_MESSAGE = "error";

	@Test
	public void shouldHandelLocationNotFoundException() {
		LocationControllerAdvice locationControllerAdvice = new LocationControllerAdvice();

		ApiError apiError = locationControllerAdvice.handleLocationNotFoundException(new LocationNotFoundException(ERROR_MESSAGE));

		assertThat(apiError.getMessage(), is(ERROR_MESSAGE));

	}
}