package me.zeph.relations.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import static java.util.Locale.getDefault;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.Mock;
import static org.mockito.MockitoAnnotations.initMocks;

public class AlleleValueDaoTest {

	private static final double DELTA = 0.0001;

	@Mock
	private ApplicationContext mockApplicationContext;

	private AlleleValueDao alleleValueDao;

	@Before
	public void setUp() throws Exception {
		initMocks(this);
		alleleValueDao = new AlleleValueDao(mockApplicationContext);
	}

	@Test
	public void shouldReturnCorrectAlleleValueWhenAlleleIsInteger() {
		when(mockApplicationContext.getMessage("kit.locus.a22", null, getDefault())).thenReturn("0.0393");
		double alleleValue = alleleValueDao.getValue("kit", "locus", 22d);
		assertEquals(0.0393, alleleValue, DELTA);
	}

	@Test
	public void shouldReturnCorrectAlleleValueWhenAlleleIsFloat() {
		when(mockApplicationContext.getMessage("kit.locus.a22.1", null, getDefault())).thenReturn("0.0393");
		double alleleValue = alleleValueDao.getValue("kit", "locus", 22.1d);
		assertEquals(0.0393, alleleValue, DELTA);
	}
}
