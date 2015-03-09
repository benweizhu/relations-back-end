package me.zeph.relations.service;

import me.zeph.relations.dao.AlleleValueDao;
import me.zeph.relations.model.CPIValue;
import me.zeph.relations.model.CpiParam;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

public class PiServiceTest {

	private static final double DELTA_3 = 0.001;

	@Mock
	private AlleleValueDao alleleValueDao;

	@Mock
	private Calculator calculator;

	private PiService piService;


	@Before
	public void setUp() throws Exception {
		initMocks(this);
		piService = new PiService(alleleValueDao, calculator);
	}

	@Test
	public void shouldCalculateCPI() {
		CpiParam cpiParam = new CpiParam();
		cpiParam.setPis(new double[]{0.5d, 0.5d, 0.5d});
		CPIValue cpi = piService.calculateCPI(cpiParam);
		assertEquals(0.125, cpi.getValue(), DELTA_3);
	}

}