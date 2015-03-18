package me.zeph.relations.service;

import me.zeph.relations.model.KitApi;
import me.zeph.relations.model.api.Kit;
import me.zeph.relations.model.entity.KitEntity;
import me.zeph.relations.repository.KitRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Locale;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

public class KitServiceTest {

	private KitService kitService;
	private KitRepository kitRepository;

	@Before
	public void setUp() throws Exception {
		kitService = new KitService();
		kitRepository = mock(KitRepository.class);
	}

	@Test
	public void shouldReturnKits() {
		ApplicationContext mockApplicationContext = mock(ApplicationContext.class);
		when(mockApplicationContext.getMessage("kits", null, Locale.getDefault())).thenReturn("Code1,Code2");

		Kit expectedKit = new Kit();
		expectedKit.setKits(newArrayList("Code1", "Code2"));

		assertThat(new KitService(mockApplicationContext).getKits(), is(expectedKit));
	}

	@Test
	public void shouldTranslateKitsToApiModel() {
		KitEntity kitEntity = getKitEntity(1, "kitName");
		when(kitRepository.findAll()).thenReturn(newArrayList(kitEntity));
		setField(kitService, "kitRepository", kitRepository);

		List<KitApi> kitApis = kitService.getAllKits();

		assertThat(kitApis.size(), is(1));
	}

	private KitEntity getKitEntity(int id, String name) {
		KitEntity kitEntity = new KitEntity();
		setField(kitEntity, "id", id);
		setField(kitEntity, "name", name);
		return kitEntity;
	}

}