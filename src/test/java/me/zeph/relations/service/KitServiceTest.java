package me.zeph.relations.service;

import me.zeph.relations.model.Kit;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import java.util.Locale;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class KitServiceTest {

	@Test
	public void shouldReturnKits() {

		ApplicationContext mockApplicationContext = mock(ApplicationContext.class);
		when(mockApplicationContext.getMessage("kits", null, Locale.getDefault())).thenReturn("Code1,Code2");

		Kit expectedKit = new Kit();
		expectedKit.setKits(newArrayList("Code1", "Code2"));

		assertThat(new KitService(mockApplicationContext).getKits(), is(expectedKit));

	}

}