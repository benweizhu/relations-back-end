package me.zeph.relations.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AlleleValueDaoTest {
	@Test
	public void shouldTranslateStringToFloat() {
		ApplicationContext mockContext = mock(ApplicationContext.class);
		when(mockContext.getMessage(anyString(), (Object[]) anyObject(), (Locale) anyObject())).thenReturn("0.001");

		assertEquals(0.001f, new AlleleValueDao(mockContext).getValue("kit", "locus", 1), 0.0001);
	}
}