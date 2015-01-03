package me.zeph.relations.service.pi.formula.oneparent;

import me.zeph.relations.model.OneParentReqParam;
import me.zeph.relations.service.pi.FormulaTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class OneInTwoQOneParentFormulaTest extends FormulaTest {

	private double c1;
	private double c2;
	private double af1;
	private double af2;

	private double c1Value;
	private double c2Value;
	private double af1Value;
	private double af2Value;

	private double expectedPi;

	public OneInTwoQOneParentFormulaTest(double c1, double c2, double af1, double af2,
	                                     double c1Value, double c2Value, double af1Value,
	                                     double af2Value, double expectedPi) {
		this.c1 = c1;
		this.c2 = c2;
		this.af1 = af1;
		this.af2 = af2;
		this.c1Value = c1Value;
		this.c2Value = c2Value;
		this.af1Value = af1Value;
		this.af2Value = af2Value;
		this.expectedPi = expectedPi;
	}

	@Parameters
	public static Collection<Double[]> data() {
		return Arrays.asList(
				new Double[][]{
						//pq qq
						{14d, 15d, 15d, 15d, 0.0393d, 0.3541d, 0.3541d, 0.3541d, 1.4120304998588d},
						//qq qr
						{15d, 15d, 15d, 16d, 0.3541d, 0.3541d, 0.3541d, 0.341d, 1.4120304998588d}
				}
		);
	}

	@Test
	public void shouldCalculateOneInTwoQ() {
		OneInTwoQOneParentFormula formula = new OneInTwoQOneParentFormula();

		OneParentReqParam reqParam = new OneParentReqParam();
		reqParam.setC1(c1);
		reqParam.setC2(c2);
		reqParam.setAf1(af1);
		reqParam.setAf2(af2);

		double actualPi = formula.calculate(c1Value, c2Value, af1Value, af2Value, reqParam);

		assertEquals(expectedPi, actualPi, DELTA_8);
	}

}