package me.zeph.relations.integration;

import com.google.common.collect.Lists;
import me.zeph.relations.configuration.WebContextConfiguration;
import me.zeph.relations.model.ParentsLocusRecord;
import me.zeph.relations.model.Unit;
import me.zeph.relations.service.Calculator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
@ContextConfiguration(classes = WebContextConfiguration.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
@WebAppConfiguration
public class CalculatorParentsIntegrationTest {

	@Autowired
	private Calculator calculator;

	private Unit c1;
	private Unit c2;
	private Unit m1;
	private Unit m2;
	private Unit af1;
	private Unit af2;
	private double expectedPi;
	private final TestContextManager testContextManager;

	private static final double DELTA = 0.0000001;
	private static final Unit A14 = new Unit(14, 0.0393d);
	private static final Unit A15 = new Unit(15, 0.3541d);
	private static final Unit A16 = new Unit(16, 0.3410d);

	public CalculatorParentsIntegrationTest(Unit c1, Unit c2, Unit m1, Unit m2, Unit af1, Unit af2, double expectedPi) {
		this.c1 = c1;
		this.c2 = c2;
		this.m1 = m1;
		this.m2 = m2;
		this.af1 = af1;
		this.af2 = af2;
		this.expectedPi = expectedPi;
		this.testContextManager = new TestContextManager(getClass());
	}

	@Parameters
	public static List<Object[]> data() {
		return Lists.newArrayList(new Object[][]{
				{A15, A15, A16, A15, A15, A15, 2.82406099971759d},
				{A15, A15, A15, A16, A15, A15, 2.82406099971759d},
				{A14, A15, A14, A14, A15, A15, 2.82406099971759d},
				{A14, A15, A15, A15, A14, A14, 25.4452926208651d},
				{A14, A15, A14, A16, A15, A15, 2.82406099971759d},
				{A14, A15, A16, A14, A15, A15, 2.82406099971759d},
				{A14, A15, A15, A16, A14, A14, 25.4452926208651d},
				{A14, A15, A16, A15, A14, A14, 25.4452926208651d},
				{A15, A15, A15, A15, A15, A15, 2.82406099971759d},
				{A14, A15, A14, A14, A15, A16, 1.4120304998588d},
				{A14, A15, A14, A14, A16, A15, 1.4120304998588d},
				{A14, A15, A15, A15, A14, A16, 12.7226463104326d},
				{A14, A15, A15, A15, A16, A14, 12.7226463104326d},
				{A14, A15, A14, A14, A14, A15, 1.4120304998588d},
				{A14, A15, A14, A14, A15, A14, 1.4120304998588d},
				{A14, A15, A15, A15, A14, A15, 12.7226463104326d},
				{A14, A15, A15, A15, A15, A14, 12.7226463104326d},
				{A14, A15, A14, A16, A15, A16, 1.4120304998588d},
				{A14, A15, A14, A16, A16, A15, 1.4120304998588d},
				{A14, A15, A16, A14, A15, A16, 1.4120304998588d},
				{A14, A15, A16, A14, A16, A15, 1.4120304998588d},
				{A14, A15, A15, A16, A14, A16, 12.7226463104326d},
				{A14, A15, A15, A16, A16, A14, 12.7226463104326d},
				{A14, A15, A16, A15, A14, A16, 12.7226463104326d},
				{A14, A15, A16, A15, A16, A14, 12.7226463104326d},
				{A14, A15, A14, A16, A14, A15, 1.4120304998588d},
				{A14, A15, A14, A16, A15, A14, 1.4120304998588d},
				{A14, A15, A16, A14, A14, A15, 1.4120304998588d},
				{A14, A15, A16, A14, A15, A14, 1.4120304998588d},
				{A14, A15, A15, A16, A14, A15, 12.7226463104326d},
				{A14, A15, A15, A16, A15, A14, 12.7226463104326d},
				{A14, A15, A16, A15, A14, A15, 12.7226463104326d},
				{A14, A15, A16, A15, A15, A14, 12.7226463104326d},
				{A15, A15, A16, A15, A15, A16, 1.4120304998588d},
				{A15, A15, A16, A15, A16, A15, 1.4120304998588d},
				{A15, A15, A15, A16, A15, A16, 1.4120304998588d},
				{A15, A15, A15, A16, A16, A15, 1.4120304998588d},
				{A15, A15, A15, A15, A15, A16, 1.4120304998588d},
				{A15, A15, A15, A15, A16, A15, 1.4120304998588d},
				{A14, A15, A14, A15, A14, A15, 2.5419420437214d},
				{A14, A15, A14, A15, A15, A14, 2.5419420437214d},
				{A14, A15, A15, A14, A14, A15, 2.5419420437214d},
				{A14, A15, A15, A14, A15, A14, 2.5419420437214d},
				{A14, A15, A14, A15, A15, A15, 2.5419420437214d},
				{A14, A15, A15, A14, A15, A15, 2.5419420437214d},
				{A14, A15, A14, A15, A14, A14, 2.5419420437214d},
				{A14, A15, A15, A14, A14, A14, 2.5419420437214d},
				{A14, A15, A14, A15, A15, A16, 1.2709710218607d},
				{A14, A15, A14, A15, A16, A15, 1.2709710218607d},
				{A14, A15, A15, A14, A15, A16, 1.2709710218607d},
				{A14, A15, A15, A14, A16, A15, 1.2709710218607d},
				{A14, A15, A14, A15, A14, A16, 1.2709710218607d},
				{A14, A15, A14, A15, A16, A14, 1.2709710218607d},
				{A14, A15, A15, A14, A14, A16, 1.2709710218607d},
				{A14, A15, A15, A14, A16, A14, 1.2709710218607d},
		});
	}

	@Before
	public void injectDependencies() throws Throwable {
		this.testContextManager.prepareTestInstance(this);
	}

	@Test
	public void shouldFindFormulaByPatternAndCalculatePi() {
		ParentsLocusRecord record = new ParentsLocusRecord(c1, c2, m1, m2, af1, af2);
		double pi = calculator.calculatePi(record.getPattern(), record.getP(), record.getQ());
		assertEquals(expectedPi, pi, DELTA);
	}
}