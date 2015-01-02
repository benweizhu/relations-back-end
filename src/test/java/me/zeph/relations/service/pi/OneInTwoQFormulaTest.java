package me.zeph.relations.service.pi;

import me.zeph.relations.model.OneParentReqParam;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OneInTwoQFormulaTest extends FormulaTest {

	@Test
	public void shouldCalculateOneInTwoQWhenInputIs_pq_qq() {
		OneInTwoQFormula oneInTwoQFormula = new OneInTwoQFormula();

		OneParentReqParam reqParam = new OneParentReqParam();
		reqParam.setC1(14);
		reqParam.setC2(15);
		reqParam.setAf1(15);
		reqParam.setAf2(15);

		double pi = oneInTwoQFormula.calculate(0.0393d, 0.3541d, 0.3541d, 0.3541d, reqParam);

		assertEquals(1.4120304998588d, pi, DELTA_8);
	}

	@Test
	public void shouldCalculateOneInTwoQWhenInputIs_qq_qr() {
		OneInTwoQFormula oneInTwoQFormula = new OneInTwoQFormula();

		OneParentReqParam reqParam = new OneParentReqParam();
		reqParam.setC1(15);
		reqParam.setC2(15);
		reqParam.setAf1(15);
		reqParam.setAf2(16);

		double pi = oneInTwoQFormula.calculate(0.3541d, 0.3541d, 0.3541d, 0.341d, reqParam);

		assertEquals(1.4120304998588d, pi, DELTA_8);
	}
}