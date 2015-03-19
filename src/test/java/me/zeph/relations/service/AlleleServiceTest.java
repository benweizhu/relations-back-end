package me.zeph.relations.service;

import me.zeph.relations.exception.AlleleNotFoundException;
import me.zeph.relations.exception.KitNotFoundException;
import me.zeph.relations.model.api.Allele;
import me.zeph.relations.model.entity.AlleleEntity;
import me.zeph.relations.model.entity.KitEntity;
import me.zeph.relations.repository.KitRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

public class AlleleServiceTest {

	private AlleleService alleleService;
	private KitRepository kitRepository;

	@Before
	public void setUp() throws Exception {
		alleleService = new AlleleService();
		kitRepository = mock(KitRepository.class);
	}

	@Test
	public void shouldTranslateAlleles() {
		KitEntity kitEntity = getKitEntity(getAlleleEntity(1L, "name"), 1L, "name");
		when(kitRepository.findOne(1L)).thenReturn(kitEntity);
		setField(alleleService, "kitRepository", kitRepository);

		List<Allele> alleles = alleleService.getAlleles(1L);

		assertThat(alleles.size(), is(1));
		assertThat(alleles.get(0).getAlleleId(), is(1L));
	}

	@Test(expected = KitNotFoundException.class)
	public void shouldThrowKitNotFoundException() {
		when(kitRepository.findOne(1L)).thenReturn(null);
		setField(alleleService, "kitRepository", kitRepository);

		alleleService.getAlleles(1L);
	}

	@Test
	public void shouldTranslateAllele() {
		KitEntity kitEntity = getKitEntity(getAlleleEntity(1L, "name"), 1L, "name");
		when(kitRepository.findOne(1L)).thenReturn(kitEntity);
		setField(alleleService, "kitRepository", kitRepository);

		Allele allele = alleleService.getAllele(1L, 1L);

		assertThat(allele.getAlleleId(), is(1L));
	}

	@Test(expected = AlleleNotFoundException.class)
	public void shouldThrowAlleleNotFoundException() {
		KitEntity kitEntity = getKitEntity(getAlleleEntity(1L, "name"), 1L, "name");
		when(kitRepository.findOne(1L)).thenReturn(kitEntity);
		setField(alleleService, "kitRepository", kitRepository);

		alleleService.getAllele(1L, 2L);
	}

	private KitEntity getKitEntity(AlleleEntity alleleEntity, long id, String name) {
		KitEntity kitEntity = new KitEntity();
		setField(kitEntity, "id", id);
		setField(kitEntity, "name", name);
		setField(kitEntity, "alleles", newArrayList(alleleEntity));
		return kitEntity;
	}

	private AlleleEntity getAlleleEntity(long id, String name) {
		AlleleEntity alleleEntity = new AlleleEntity();
		setField(alleleEntity, "id", id);
		setField(alleleEntity, "name", name);
		return alleleEntity;
	}
}