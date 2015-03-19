package me.zeph.relations.service;

import me.zeph.relations.model.api.Allele;
import me.zeph.relations.model.entity.AlleleEntity;
import me.zeph.relations.repository.AlleleRepository;
import org.junit.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

public class AlleleServiceTest {

	@Test
	public void shouldTranslateAllele() {
		AlleleService alleleService = new AlleleService();
		AlleleRepository alleleRepository = mock(AlleleRepository.class);
		AlleleEntity alleleEntity = getAlleleEntity(1L, "name");
		when(alleleRepository.findAll()).thenReturn(newArrayList(alleleEntity));
		setField(alleleService, "alleleRepository", alleleRepository);
		List<Allele> alleles = alleleService.getAlleles(1L);
		assertThat(alleles.size(), is(1));
		assertThat(alleles.get(0).getAlleleId(), is(1L));
	}

	private AlleleEntity getAlleleEntity(long id, String name) {
		AlleleEntity alleleEntity = new AlleleEntity();
		setField(alleleEntity, "id", id);
		setField(alleleEntity, "name", name);
		return alleleEntity;
	}
}