package me.zeph.relations.advice;

import me.zeph.relations.model.api.ApiError;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.TypeMismatchException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CommonControllerAdviceTest {

	public static final String ERROR_MESSAGE = "error";
	private CommonControllerAdvice commonControllerAdvice;
	private TypeMismatchException typeMismatchException;

	@Before
	public void setUp() throws Exception {
		commonControllerAdvice = new CommonControllerAdvice();
		typeMismatchException = mock(TypeMismatchException.class);
	}

	@Test
	public void shouldHandleTypeMismatchException() {
		when(typeMismatchException.getMessage()).thenReturn(ERROR_MESSAGE);

		ApiError apiError = commonControllerAdvice.handleTypeMismatchException(typeMismatchException);

		assertThat(apiError.getMessage(), is(ERROR_MESSAGE));
	}

	@Test
	public void shouldHandleInternalServerErrorException() {
		ApiError apiError = commonControllerAdvice.handleInternalServerError(new Exception("any error"));

		assertThat(apiError.getMessage(), is("INTERNAL SERVER ERROR"));
	}

}