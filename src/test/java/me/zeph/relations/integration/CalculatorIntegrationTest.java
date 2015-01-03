package me.zeph.relations.integration;

import me.zeph.relations.config.WebContextConfiguration;
import me.zeph.relations.model.OneParentLocusRecord;
import me.zeph.relations.model.Unit;
import me.zeph.relations.service.Calculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebContextConfiguration.class)
@WebAppConfiguration
public class CalculatorIntegrationTest {

	@Autowired
	private Calculator calculator;

	@Test
	public void shouldFindFormulaByPattenAndCalculatePi() {
		Unit c1 = new Unit(14, 0.0393d);
		Unit c2 = new Unit(15, 0.3541);
		Unit af1 = new Unit(14, 0.0393d);
		Unit af2 = new Unit(15, 0.3541);
		OneParentLocusRecord record = new OneParentLocusRecord(c1, c2, af1, af2);
		double pi = calculator.calculatePi(record.getPattern(), record.getP(), record.getQ());
		assertEquals(7.06733840514568d, pi, 0.0000001);
	}
}