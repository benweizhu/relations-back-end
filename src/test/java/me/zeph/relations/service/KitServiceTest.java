package me.zeph.relations.service;

import me.zeph.relations.model.Kit;
import me.zeph.relations.model.entity.KitEntity;
import me.zeph.relations.repository.KitRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

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
	public void shouldTranslateKitsToApiModel() {
		KitEntity kitEntity = getKitEntity(1, "kitName");
		when(kitRepository.findAll()).thenReturn(newArrayList(kitEntity));
		setField(kitService, "kitRepository", kitRepository);

		List<Kit> kits = kitService.getAllKits();

		assertThat(kits.size(), is(1));
	}

	private KitEntity getKitEntity(int id, String name) {
		KitEntity kitEntity = new KitEntity();
		setField(kitEntity, "id", id);
		setField(kitEntity, "name", name);
		return kitEntity;
	}

}