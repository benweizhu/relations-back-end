package me.zeph.relations.service;

import com.google.common.collect.Lists;
import me.zeph.relations.exception.KitAlreadyExistException;
import me.zeph.relations.exception.KitNotFoundException;
import me.zeph.relations.model.api.Kit;
import me.zeph.relations.model.entity.LocusEntity;
import me.zeph.relations.model.entity.KitEntity;
import me.zeph.relations.repository.KitRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.ReflectionTestUtils.getField;
import static org.springframework.test.util.ReflectionTestUtils.setField;

public class KitServiceTest {

	private static final long ID = 1L;
	private static final String KIT_NAME = "kitName";
	private static final String ALLELE_NAME = "alleleName";
	private static final long ALLELE_ID = 1L;
	private static final long ANOTHER_KIT_ID = 2L;
	private static final String ANOTHER_KIT_NAME = "anotherKitName";
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
		KitEntity kitEntity = getKitEntity(ID, KIT_NAME);
		when(kitRepository.findAll()).thenReturn(newArrayList(kitEntity));

		List<Kit> kits = kitService.getKits();

		assertThat(kits.size(), is(1));
	}

	@Test
	public void shouldGetKitByKitId() {
		KitEntity kitEntity = getKitEntity(ID, KIT_NAME);
		when(kitRepository.findOne(ID)).thenReturn(kitEntity);

		Kit kit = kitService.getKit(ID);

		assertThat((long) getField(kit, "kitId"), is(ID));
	}

	@Test(expected = KitNotFoundException.class)
	public void shouldThrowKitNotFoundException() {
		when(kitRepository.findOne(ID)).thenReturn(null);

		kitService.getKit(ID);
	}

	@Test
	public void shouldSaveKit() {
		when(kitRepository.findByName(anyString())).thenReturn(Lists.<KitEntity>newArrayList());
		when(kitRepository.saveAndFlush((KitEntity) anyObject())).thenReturn(getKitEntity(ID, KIT_NAME));

		Kit kit = kitService.addKit(KIT_NAME);

		assertThat(kit.getName(), is(KIT_NAME));
	}

	@Test(expected = KitAlreadyExistException.class)
	public void shouldThrowKitAlreadyExistException() {
		when(kitRepository.findByName(anyString())).thenReturn(newArrayList(new KitEntity()));

		kitService.addKit(KIT_NAME);
	}

	@Test
	public void shouldRemoveKitSuccessfully() {
		KitEntity kitEntity = getKitEntity(ID, KIT_NAME);
		LocusEntity allele = getAlleleEntity(ALLELE_ID, ALLELE_NAME);
		KitEntity anotherKitEntity = getKitEntity(ANOTHER_KIT_ID, ANOTHER_KIT_NAME);
		kitEntity.addLocus(allele);
		anotherKitEntity.addLocus(allele);
		when(kitRepository.findOne(ID)).thenReturn(kitEntity);

		kitService.removeKit(ID);

		assertThat(allele.getKits().size(), is(1));
		assertThat(kitEntity.getLoci().isEmpty(), is(true));
	}

	@Test(expected = KitNotFoundException.class)
	public void shouldThrowKitNotFoundExceptionWhenRemoveKitWhichIsNotExist() {
		when(kitRepository.findOne(ID)).thenReturn(null);
		kitService.removeKit(ID);
	}

	private LocusEntity getAlleleEntity(long alleleId, String alleleName) {
		LocusEntity allele = new LocusEntity();
		setField(allele, "id", alleleId);
		setField(allele, "name", alleleName);
		return allele;
	}

	private KitEntity getKitEntity(long id, String name) {
		KitEntity kitEntity = new KitEntity();
		setField(kitEntity, "id", id);
		setField(kitEntity, "name", name);
		return kitEntity;
	}

}