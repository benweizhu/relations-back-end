package me.zeph.relations.service;

import me.zeph.relations.exception.KitNotFoundException;
import me.zeph.relations.exception.LocusAlreadyExistException;
import me.zeph.relations.exception.LocusNotFoundException;
import me.zeph.relations.model.api.Locus;
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

public class LocusServiceTest {

	private static final String ALLELE_NAME = "alleleName";
	private static final String KIT_NAME = "name";
	private static final long ALLELE_ID = 2L;
	private static final long KIT_ID = 1L;
	private LocusService locusService;
	private KitRepository kitRepository;
	private AlleleRepository alleleRepository;

	@Before
	public void setUp() throws Exception {
		locusService = new LocusService();
		kitRepository = mock(KitRepository.class);
		alleleRepository = mock(AlleleRepository.class);
		setField(locusService, "kitRepository", kitRepository);
		setField(locusService, "alleleRepository", alleleRepository);
	}

	@Test
	public void shouldTranslateAlleles() {
		KitEntity kitEntity = getKitEntity(getAlleleEntity(KIT_ID, ALLELE_NAME), KIT_ID, KIT_NAME, true);
		when(kitRepository.findOne(KIT_ID)).thenReturn(kitEntity);

		List<Locus> loci = locusService.getLoci(KIT_ID);

		assertThat(loci.size(), is(1));
		assertThat(loci.get(0).getAlleleId(), is(KIT_ID));
	}

	@Test(expected = KitNotFoundException.class)
	public void shouldThrowKitNotFoundException() {
		when(kitRepository.findOne(KIT_ID)).thenReturn(null);

		locusService.getLoci(KIT_ID);
	}

	@Test
	public void shouldTranslateAllele() {
		KitEntity kitEntity = getKitEntity(getAlleleEntity(KIT_ID, ALLELE_NAME), KIT_ID, KIT_NAME, true);
		when(kitRepository.findOne(KIT_ID)).thenReturn(kitEntity);

		Locus locus = locusService.getLocus(KIT_ID, KIT_ID);

		assertThat(locus.getAlleleId(), is(KIT_ID));
	}

	@Test(expected = LocusNotFoundException.class)
	public void shouldThrowAlleleNotFoundException() {
		KitEntity kitEntity = getKitEntity(getAlleleEntity(KIT_ID, ALLELE_NAME), KIT_ID, KIT_NAME, true);
		when(kitRepository.findOne(KIT_ID)).thenReturn(kitEntity);

		locusService.getLocus(KIT_ID, ALLELE_ID);
	}

	@Test
	public void shouldAddAlleleToKitSuccessfullyWhenAlleleNotExistInDB() {
		KitEntity kitEntity = getKitEntity(getAlleleEntity(KIT_ID, ALLELE_NAME), KIT_ID, KIT_NAME, false);
		when(kitRepository.findOne(KIT_ID)).thenReturn(kitEntity);
		when(alleleRepository.findByName(anyString())).thenReturn(null);

		locusService.addLocus(KIT_ID, "newAllele");

		assertThat(kitEntity.getAlleles().size(), is(1));
		assertThat(kitEntity.getAlleles().get(0).getName(), is("newAllele"));
	}

	@Test
	public void shouldAddAlleleToKitSuccessfullyWhenAlleleAlreadyExistInDdButNotLinked() {
		AlleleEntity alleleEntity = getAlleleEntity(ALLELE_ID, ALLELE_NAME);
		KitEntity kitEntity = getKitEntity(alleleEntity, KIT_ID, KIT_NAME, false);
		when(kitRepository.findOne(KIT_ID)).thenReturn(kitEntity);
		when(alleleRepository.findByName(anyString())).thenReturn(alleleEntity);

		locusService.addLocus(KIT_ID, KIT_NAME);

		assertThat(kitEntity.getAlleles().size(), is(1));
		assertThat(kitEntity.getAlleles().get(0).getName(), is(ALLELE_NAME));
	}

	@Test(expected = KitNotFoundException.class)
	public void shouldThrowKitNotFoundExceptionWhenAddAllele() {
		when(kitRepository.findOne(KIT_ID)).thenReturn(null);

		locusService.addLocus(KIT_ID, KIT_NAME);
	}

	@Test(expected = LocusAlreadyExistException.class)
	public void shouldAlleleAlreadyExistExceptionWhenAddAllele() {
		AlleleEntity alleleEntity = getAlleleEntity(ALLELE_ID, ALLELE_NAME);
		KitEntity kitEntity = getKitEntity(alleleEntity, KIT_ID, KIT_NAME, true);
		when(kitRepository.findOne(KIT_ID)).thenReturn(kitEntity);
		when(alleleRepository.findByName(anyString())).thenReturn(alleleEntity);

		locusService.addLocus(KIT_ID, KIT_NAME);
	}

	@Test
	public void shouldRemoveAllele() {
		AlleleEntity alleleEntity = getAlleleEntity(ALLELE_ID, ALLELE_NAME);
		KitEntity kitEntity = getKitEntity(alleleEntity, KIT_ID, KIT_NAME, true);
		when(kitRepository.findOne(KIT_ID)).thenReturn(kitEntity);
		when(alleleRepository.findOne(ALLELE_ID)).thenReturn(alleleEntity);

		locusService.removeLocus(KIT_ID, ALLELE_ID);

		assertThat(kitEntity.getAlleles().isEmpty(), is(true));
		assertThat(alleleEntity.getKits().isEmpty(), is(true));
		verify(kitRepository).saveAndFlush((KitEntity) anyObject());
	}

	@Test(expected = KitNotFoundException.class)
	public void shouldThrowKitNotFoundExceptionWhenRemoveAllele() {
		when(kitRepository.findOne(99L)).thenReturn(null);

		locusService.removeLocus(99L, KIT_ID);

		verify(kitRepository).findOne(99L);
	}

	@Test(expected = LocusNotFoundException.class)
	public void shouldThrowAlleleNotFoundExceptionWhenAlleleNotExistInDB() {
		AlleleEntity alleleEntity = getAlleleEntity(ALLELE_ID, ALLELE_NAME);
		KitEntity kitEntity = getKitEntity(alleleEntity, KIT_ID, KIT_NAME, true);
		when(kitRepository.findOne(KIT_ID)).thenReturn(kitEntity);
		when(alleleRepository.findOne(99L)).thenReturn(null);

		locusService.removeLocus(KIT_ID, 99L);

		verify(alleleRepository).findOne(99L);
	}

	@Test(expected = LocusNotFoundException.class)
	public void shouldThrowAlleleNotFoundExceptionWhenKitAlleleRelationshipNotExistInDB() {
		AlleleEntity alleleEntity = getAlleleEntity(ALLELE_ID, ALLELE_NAME);
		KitEntity kitEntity = getKitEntity(alleleEntity, KIT_ID, KIT_NAME, false);
		when(kitRepository.findOne(KIT_ID)).thenReturn(kitEntity);
		when(alleleRepository.findOne(ALLELE_ID)).thenReturn(alleleEntity);

		locusService.removeLocus(KIT_ID, KIT_ID);

		verify(alleleRepository).findOne(KIT_ID);
	}

	private KitEntity getKitEntity(AlleleEntity alleleEntity, long id, String name, boolean addLink) {
		KitEntity kitEntity = new KitEntity();
		setField(kitEntity, "id", id);
		setField(kitEntity, KIT_NAME, name);
		if (addLink) {
			setField(kitEntity, "alleles", newArrayList(alleleEntity));
			alleleEntity.getKits().add(kitEntity);
		}
		return kitEntity;
	}

	private AlleleEntity getAlleleEntity(long id, String name) {
		AlleleEntity alleleEntity = new AlleleEntity();
		setField(alleleEntity, "id", id);
		setField(alleleEntity, KIT_NAME, name);
		return alleleEntity;
	}
}