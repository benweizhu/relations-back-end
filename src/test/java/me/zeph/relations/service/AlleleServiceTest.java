package me.zeph.relations.service;

import me.zeph.relations.exception.AlleleNotFoundException;
import me.zeph.relations.exception.KitNotFoundException;
import me.zeph.relations.model.api.Allele;
import me.zeph.relations.model.entity.AlleleEntity;
import me.zeph.relations.model.entity.KitEntity;
import me.zeph.relations.repository.AlleleRepository;
import me.zeph.relations.repository.KitRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.ReflectionTestUtils.setField;

public class AlleleServiceTest {

	private AlleleService alleleService;
	private KitRepository kitRepository;
	private AlleleRepository alleleRepository;

	@Before
	public void setUp() throws Exception {
		alleleService = new AlleleService();
		kitRepository = mock(KitRepository.class);
		alleleRepository = mock(AlleleRepository.class);
		setField(alleleService, "kitRepository", kitRepository);
		setField(alleleService, "alleleRepository", alleleRepository);
	}

	@Test
	public void shouldTranslateAlleles() {
		KitEntity kitEntity = getKitEntity(getAlleleEntity(1L, "name"), 1L, "name");
		when(kitRepository.findOne(1L)).thenReturn(kitEntity);

		List<Allele> alleles = alleleService.getAlleles(1L);

		assertThat(alleles.size(), is(1));
		assertThat(alleles.get(0).getAlleleId(), is(1L));
	}

	@Test(expected = KitNotFoundException.class)
	public void shouldThrowKitNotFoundException() {
		when(kitRepository.findOne(1L)).thenReturn(null);

		alleleService.getAlleles(1L);
	}

	@Test
	public void shouldTranslateAllele() {
		KitEntity kitEntity = getKitEntity(getAlleleEntity(1L, "name"), 1L, "name");
		when(kitRepository.findOne(1L)).thenReturn(kitEntity);

		Allele allele = alleleService.getAllele(1L, 1L);

		assertThat(allele.getAlleleId(), is(1L));
	}

	@Test(expected = AlleleNotFoundException.class)
	public void shouldThrowAlleleNotFoundException() {
		KitEntity kitEntity = getKitEntity(getAlleleEntity(1L, "name"), 1L, "name");
		when(kitRepository.findOne(1L)).thenReturn(kitEntity);

		alleleService.getAllele(1L, 2L);
	}

	@Test
	public void shouldRemoveAllele() {
		AlleleEntity alleleEntity = getAlleleEntity(1L, "name");
		KitEntity kitEntity = getKitEntity(alleleEntity, 1L, "name");
		when(kitRepository.findOne(1L)).thenReturn(kitEntity);
		when(alleleRepository.findOne(1L)).thenReturn(alleleEntity);

		alleleService.removeAllele(1L, 1L);

		assertThat(kitEntity.getAlleles().isEmpty(), is(true));
		assertThat(alleleEntity.getKits().isEmpty(), is(true));
		verify(kitRepository).saveAndFlush((KitEntity) anyObject());
	}

	@Test(expected = KitNotFoundException.class)
	public void shouldThrowKitNotFoundExceptionWhenRemoveAllele() {
		when(kitRepository.findOne(99L)).thenReturn(null);

		alleleService.removeAllele(99L, 1L);

		verify(kitRepository).findOne(99L);
	}

	@Test(expected = AlleleNotFoundException.class)
	public void shouldThrowAlleleNotFoundExceptionWhenRemoveAllele() {
		AlleleEntity alleleEntity = getAlleleEntity(1L, "name");
		KitEntity kitEntity = getKitEntity(alleleEntity, 1L, "name");
		when(kitRepository.findOne(1L)).thenReturn(kitEntity);
		when(alleleRepository.findOne(99L)).thenReturn(null);

		alleleService.removeAllele(1L, 99L);

		verify(alleleRepository).findOne(99L);
	}

	private KitEntity getKitEntity(AlleleEntity alleleEntity, long id, String name) {
		KitEntity kitEntity = new KitEntity();
		setField(kitEntity, "id", id);
		setField(kitEntity, "name", name);
		setField(kitEntity, "alleles", newArrayList(alleleEntity));
		alleleEntity.getKits().add(kitEntity);
		return kitEntity;
	}

	private AlleleEntity getAlleleEntity(long id, String name) {
		AlleleEntity alleleEntity = new AlleleEntity();
		setField(alleleEntity, "id", id);
		setField(alleleEntity, "name", name);
		return alleleEntity;
	}
}