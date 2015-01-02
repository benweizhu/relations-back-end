package me.zeph.relations.service.pi;

import me.zeph.relations.model.OneParentReqParam;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OneInQFormulaTest extends FormulaTest {

	@Test
	public void shouldCalculateOneInQWhenInputIs_qq_qq() {
		OneInQFormula oneInQFormula = new OneInQFormula();

		OneParentReqParam reqParam = new OneParentReqParam();
		reqParam.setAf1(15);
		reqParam.setAf2(15);
		reqParam.setC1(15);
		reqParam.setC2(15);

		double pi = oneInQFormula.calculate(0.0393d, 0.0393d, 0.0393d, 0.0393d, reqParam);

		assertEquals(25.4452926208651d, pi, DELTA_8);
	}
}