package me.zeph.relations.service;

import me.zeph.relations.exception.KitNotFoundException;
import me.zeph.relations.model.api.Kit;
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
import static org.springframework.test.util.ReflectionTestUtils.getField;
import static org.springframework.test.util.ReflectionTestUtils.setField;

public class KitServiceTest {

	private KitService kitService;
	private KitRepository kitRepository;

	@Before
	public void setUp() throws Exception {
		kitService = new KitService();
		kitRepository = mock(KitRepository.class);
		setField(kitService, "kitRepository", kitRepository);
	}

	@Test
	public void shouldTranslateKitsToApiModel() {
		KitEntity kitEntity = getKitEntity(1L, "kitName");
		when(kitRepository.findAll()).thenReturn(newArrayList(kitEntity));

		List<Kit> kits = kitService.getKits();

		assertThat(kits.size(), is(1));
	}

	@Test
	public void shouldGetKitByKitId() {
		KitEntity kitEntity = getKitEntity(1L, "kitName");
		when(kitRepository.findOne(1L)).thenReturn(kitEntity);

		Kit kit = kitService.getKit(1L);

		assertThat((long) getField(kit, "kitId"), is(1L));
	}

	@Test(expected = KitNotFoundException.class)
	public void shouldThrowKitNotFoundException(){
		when(kitRepository.findOne(1L)).thenReturn(null);

		kitService.getKit(1L);
	}

	private KitEntity getKitEntity(long id, String name) {
		KitEntity kitEntity = new KitEntity();
		setField(kitEntity, "id", id);
		setField(kitEntity, "name", name);
		return kitEntity;
	}

}