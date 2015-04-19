package me.zeph.relations.service;

import me.zeph.relations.model.api.CPIValue;
import me.zeph.relations.model.api.RCPValue;
import me.zeph.relations.model.api.RcpParam;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.springframework.test.util.ReflectionTestUtils.setField;

public class PiServiceTest {

	private static final double DELTA_3 = 0.001;
	private static final double DELTA_7 = 0.0000001;
	private CalculatorService calculatorService;
	private PiService piService;

	@Before
	public void setUp() throws Exception {
		calculatorService = mock(CalculatorService.class);
		piService = new PiService();
		setField(piService, "calculatorService", calculatorService);
	}

	@Test
	public void shouldCalculateCPI() {
		CPIValue cpi = piService.calculateCPI(new double[]{0.5d, 0.5d, 0.5d});
		assertEquals(0.125, cpi.getValue(), DELTA_3);
	}

	@Test
	public void shouldCalculateRCP() {
		RcpParam rcpParam = new RcpParam();
		rcpParam.setCpi(0.125);
		RCPValue rcpValue = piService.calculateRCP(rcpParam);
		assertEquals(0.1111111, rcpValue.getValue(), DELTA_7);
	}

}