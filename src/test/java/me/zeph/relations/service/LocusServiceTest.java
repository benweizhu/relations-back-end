package me.zeph.relations.service;

import me.zeph.relations.exception.KitNotExistException;
import me.zeph.relations.model.LocusCode;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LocusServiceTest {

	private ApplicationContext mockApplicationContext;
	private LocusService locusService;

	@Before
	public void setUp() throws Exception {
		mockApplicationContext = mock(ApplicationContext.class);
		locusService = new LocusService(mockApplicationContext);
	}

	@Test
	public void shouldReturnLocusCode() throws Exception {
		when(mockApplicationContext.getMessage("kit", null, Locale.getDefault())).thenReturn("Code1,Code2");
		LocusCode expectedLocusCode = createLocusCode("Code1", "Code2");

		LocusCode kit = locusService.getLocusCode("kit");

		assertThat(kit, is(expectedLocusCode));
	}

	@Test(expected = KitNotExistException.class)
	public void shouldThrowKitNotExistedException() throws Exception {
		when(mockApplicationContext.getMessage("kit", null, Locale.getDefault()))
				.thenThrow(new NoSuchMessageException("kit not exist for: kit"));
		locusService.getLocusCode("kit");
	}

	private LocusCode createLocusCode(String... codes) {
		LocusCode expectedLocusCode = new LocusCode();
		expectedLocusCode.setCodes(newArrayList(codes));
		return expectedLocusCode;
	}
}