package me.zeph.relations.service.pi;

import me.zeph.relations.model.OneParentReqParam;
import me.zeph.relations.service.pi.formula.oneparent.PPlusQInFourPQFormula;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PPlusQInFourPQFormulaTest extends FormulaTest {
	@Test
	public void shouldCalculatePPlusQInForPQWhenInputIs_pq_pq() {
		PPlusQInFourPQFormula pPlusQInFourPQFormula = new PPlusQInFourPQFormula();
		OneParentReqParam reqParam = new OneParentReqParam();
		reqParam.setC1(14);
		reqParam.setC2(15);
		reqParam.setAf1(14);
		reqParam.setAf2(15);

		double pi = pPlusQInFourPQFormula.calculate(0.0393d, 0.3541d, 0.0393d, 0.3541d, reqParam);

		assertEquals(7.06733840514568d, pi, DELTA_8);
	}
}