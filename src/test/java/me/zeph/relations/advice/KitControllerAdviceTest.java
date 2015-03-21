package me.zeph.relations.advice;

import me.zeph.relations.exception.KitAlreadyExistException;
import me.zeph.relations.exception.KitNotFoundException;
import me.zeph.relations.model.api.ApiError;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class KitControllerAdviceTest {

	private static final String ERROR_MESSAGE = "error";

	@Test
	public void shouldHandelKitNotFoundException() {
		KitControllerAdvice kitControllerAdvice = new KitControllerAdvice();

		ApiError apiError = kitControllerAdvice.handleKitNotFoundException(new KitNotFoundException(ERROR_MESSAGE));

		assertThat(apiError.getMessage(), is(ERROR_MESSAGE));

	}

	@Test
	public void shouldHandelKitAlreadyExistException() {
		KitControllerAdvice kitControllerAdvice = new KitControllerAdvice();

		ApiError apiError = kitControllerAdvice.handleKitAlreadyExistException(new KitAlreadyExistException(ERROR_MESSAGE));

		assertThat(apiError.getMessage(), is(ERROR_MESSAGE));
	}
}