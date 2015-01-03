package me.zeph.relations.service.pi.formula.oneparent;

import me.zeph.relations.model.OneParentReqParam;
import me.zeph.relations.service.pi.FormulaTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OneInFourQOneParentFormulaTest extends FormulaTest {
	@Test
	public void shouldCalculateOneInFourQWhenInputIs_pq_qr() {
		OneInFourQOneParentFormula oneInFourQOneParentFormula = new OneInFourQOneParentFormula();
		OneParentReqParam reqParam = new OneParentReqParam();
		reqParam.setC1(14);
		reqParam.setC2(15);
		reqParam.setAf1(15);
		reqParam.setAf2(16);

		double pi = oneInFourQOneParentFormula.calculate(0.0393d, 0.3541d, 0.3541d, 0.341d, reqParam);

		assertEquals(0.706015249929398d, pi, DELTA_8);
	}
}