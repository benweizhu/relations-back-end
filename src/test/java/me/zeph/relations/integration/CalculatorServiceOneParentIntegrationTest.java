package me.zeph.relations.integration;

import com.google.common.collect.Lists;
import me.zeph.relations.Application;
import me.zeph.relations.model.OneParentLocusRecord;
import me.zeph.relations.model.Unit;
import me.zeph.relations.service.CalculatorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class CalculatorServiceOneParentIntegrationTest {

	@Autowired
	private CalculatorService calculatorService;

	private Unit c1;
	private Unit c2;
	private Unit af1;
	private Unit af2;
	private double expectedPi;
	private final TestContextManager testContextManager;

	private static final double DELTA = 0.0000001;
	private static final Unit A14 = new Unit(14, 0.0393d);
	private static final Unit A15 = new Unit(15, 0.3541d);
	private static final Unit A16 = new Unit(16, 0.3410d);

	public CalculatorServiceOneParentIntegrationTest(Unit c1, Unit c2, Unit af1, Unit af2, double expectedPi) {
		this.c1 = c1;
		this.c2 = c2;
		this.af1 = af1;
		this.af2 = af2;
		this.expectedPi = expectedPi;
		this.testContextManager = new TestContextManager(getClass());
	}

	@Parameters
	public static List<Object[]> data() {
		return Lists.newArrayList(new Object[][]{
				//qq qq
				{A15, A15, A15, A15, 2.82406099971759d},
				//pq qq
				{A14, A15, A15, A15, 1.4120304998588d},
				//pq pp
				{A14, A15, A14, A14, 12.7226463104326d},
				//qq qr
				{A15, A15, A15, A16, 1.4120304998588d},
				//qq rq
				{A15, A15, A16, A15, 1.4120304998588d},
				//pq pq
				{A14, A15, A14, A15, 7.06733840514568d},
				//pq qp
				{A14, A15, A15, A14, 7.06733840514568d},
				//pq qr
				{A14, A15, A15, A16, 0.706015249929398d},
				//pq rq
				{A14, A15, A16, A15, 0.706015249929398d},
				//pq pr
				{A14, A15, A14, A16, 6.36132315521628d},
				//pq rp
				{A14, A15, A16, A14, 6.36132315521628d}
		});
	}

	@Before
	public void injectDependencies() throws Throwable {
		this.testContextManager.prepareTestInstance(this);
	}

	@Test
	public void shouldFindFormulaByPatternAndCalculatePi() {
		OneParentLocusRecord record = new OneParentLocusRecord(c1, c2, af1, af2);
		double pi = calculatorService.calculatePi(record.getPattern(), record.getP(), record.getQ());
		assertEquals(expectedPi, pi, DELTA);
	}
}