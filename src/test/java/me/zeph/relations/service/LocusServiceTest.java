package me.zeph.relations.service;

import me.zeph.relations.model.LocusCode;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import java.util.Locale;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LocusServiceTest {

	@Test
	public void shouldReturnLocusCode() {

		ApplicationContext mockApplicationContext = mock(ApplicationContext.class);
		when(mockApplicationContext.getMessage("kit", null, Locale.getDefault())).thenReturn("Code1,Code2");

		LocusCode expectedLocusCode = new LocusCode();
		expectedLocusCode.setCodes(newArrayList("Code1", "Code2"));

		assertThat(new LocusService(mockApplicationContext).getLocusCode("kit"), is(expectedLocusCode));

	}
}